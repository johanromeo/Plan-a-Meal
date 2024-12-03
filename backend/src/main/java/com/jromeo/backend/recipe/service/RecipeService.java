package com.jromeo.backend.recipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jromeo.backend.exceptions.RecipeNotFoundException;
import com.jromeo.backend.recipe.dto.RecipeInstructionDto;
import com.jromeo.backend.recipe.RecipePromptBuilder;
import com.jromeo.backend.recipe.RecipeResponseParser;
import com.jromeo.backend.recipe.dto.RecipeResponseDto;
import com.jromeo.backend.recipe.entity.RecipeResponseEntity;
import com.jromeo.backend.recipe.mapper.RecipeMapper;
import com.jromeo.backend.recipe.repository.RecipeRepository;
import com.jromeo.backend.openai.chatgpt.api.ChatGptApi;
import com.jromeo.backend.openai.chatgpt.request.RequestBuilder;
import com.jromeo.backend.openai.chatgpt.request.RequestBuilder.ModelType;
import com.jromeo.backend.openai.chatgpt.request.RequestMessage;
import com.jromeo.backend.openai.chatgpt.request.RequestMessage.Role;
import com.jromeo.backend.openai.chatgpt.request.RequestResponseFormat;
import com.jromeo.backend.openai.chatgpt.request.RequestResponseFormat.FormatType;
import com.jromeo.backend.provision.service.ProvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Service class that uses {@link ChatGptApi} to call the API with pre-configured system prompts
 * and customizable user instruction-prompts, using {@link RecipePromptBuilder} to generate a recipe
 * based on the household's available provisions.
 *
 * Also providing CRUD operations on {@link RecipeResponseDto} obejct(s).
 *
 * @author Johan Romeo
 */
@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipePromptBuilder promptBuilder;
    private final ProvisionService provisionService;
    private final RecipeRepository recipeRepository;
    private final RecipeResponseParser parser;
    private final RecipeMapper recipeMapper;
    private final ChatGptApi api;

    /**
     * Method for calling the API with pre-configured system prompts, generating a recipe based
     * on the user's instructions and available provisions.
     *
     * @param instructions the recipe instructions set by the user.
     * @return the user's recipe instructions.
     * @throws JsonProcessingException the json processing exception
     */
    public RecipeResponseDto generateRecipe(RecipeInstructionDto instructions) throws JsonProcessingException {
        // The system prompt containing pre-defined instructions by the program's author to minimize hallucinations.
        // Also uses the user's specific recipe instructions, giving the user a variety of options.
        String systemPrompt = promptBuilder.promptChatGptForRecipe(instructions);
        RequestMessage systemMessage = new RequestMessage(
                Role.SYSTEM,
                systemPrompt
        );

        // The household's available provisions needed to generate a recipe.
        String userAvailableProvisions = promptBuilder.loadHouseholdAvailableProvisions(provisionService.findAllPositiveProvisions());
        RequestMessage userMessage = new RequestMessage(
                Role.USER,
                userAvailableProvisions
        );

        // JSON response, later to be mapped to RecipeDto for convenience.
        RequestResponseFormat jsonAsResponseBody = new RequestResponseFormat(
                FormatType.JSON_OBJECT
        );

        // Constructing the final prompt, containing everything ChatGPT needs in order to generate the recipe.
        RequestBuilder requestBuilderBody = new RequestBuilder(
                ModelType.GPT_3_5_TURBO,
                List.of(systemMessage, userMessage),
                jsonAsResponseBody
        );

        String responseBody = api.callChatGptApi(requestBuilderBody);

        RecipeResponseDto recipeResponseDto = parser.parseChatGptResponse(responseBody);

        recipeRepository.save(recipeMapper.mapToEntity(recipeResponseDto));

        return recipeResponseDto;
    }

    public RecipeResponseDto getRecipeById(Integer id) throws IOException {
        RecipeResponseEntity recipeResponseEntity = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with id " + id + " doesn't exist."));

        return recipeMapper.mapToDto(recipeResponseEntity);
    }

    public List<RecipeResponseDto> getAllRecipes() throws IOException {
        List<RecipeResponseEntity> recipeEntities = recipeRepository.findAll();

        return recipeMapper.mapToDtos(recipeEntities);
    }

    public void deleteRecipe(Integer id) {
        RecipeResponseEntity recipeResponseEntity = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with id " + id + " doesn't exist."));

        recipeRepository.delete(recipeResponseEntity);
    }
}
