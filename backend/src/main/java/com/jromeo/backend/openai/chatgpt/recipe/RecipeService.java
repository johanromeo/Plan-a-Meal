package com.jromeo.backend.openai.chatgpt.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jromeo.backend.openai.chatgpt.recipe.dto.RecipeDto;
import com.jromeo.backend.openai.chatgpt.recipe.mapper.RecipeMapper;
import com.jromeo.backend.openai.chatgpt.recipe.repository.RecipeRepository;
import com.jromeo.backend.openai.chatgpt.recipe.service.ChatGptApi;
import com.jromeo.backend.openai.chatgpt.request.RequestBuilderDto;
import com.jromeo.backend.openai.chatgpt.request.RequestMessage;
import com.jromeo.backend.openai.chatgpt.request.RequestMessage.Role;
import com.jromeo.backend.openai.chatgpt.request.RequestResponseFormat;
import com.jromeo.backend.openai.chatgpt.request.RequestResponseFormat.FormatType;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final ChatGptApi api;
    private final RecipePromptBuilder promptBuilder;
    private final RecipeResponseParser parser;
    private final ProvisionService provisionService;
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeService(ChatGptApi api, RecipePromptBuilder promptBuilder, RecipeResponseParser parser,
                         ProvisionService provisionService, RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.api = api;
        this.promptBuilder = promptBuilder;
        this.parser = parser;
        this.provisionService = provisionService;
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    public RecipeDto generateRecipe(RecipeInstructionDto instructions) throws JsonProcessingException {
        // System specific prompts and settings
        String systemPrompt = promptBuilder.buildSystemPrompt(instructions);
        RequestMessage systemMessage = new RequestMessage(
                Role.SYSTEM,
                systemPrompt
        );

        // User specific prompts and settings
        String provisions = promptBuilder.buildUserPrompt(provisionService.findAllPositiveProvisions());
        RequestMessage userMessage = new RequestMessage(
                Role.USER,
                provisions
        );

        // Response as JSON, later to be mapped to RecipeDto
        RequestResponseFormat jsonAsResponseFormat = new RequestResponseFormat(
                FormatType.JSON_OBJECT
        );

        // Build the prompt
        RequestBuilderDto requestBuilderBody = new RequestBuilderDto(
                "gpt-3.5-turbo",
                List.of(systemMessage, userMessage),
                jsonAsResponseFormat
        );

        String responseBody = api.callChatGptApi(requestBuilderBody);

        RecipeDto recipeDto = parser.parseResponse(responseBody);

        // Should be in separate service class
        recipeRepository.save(recipeMapper.mapToEntity(recipeDto));

        return recipeDto;
    }
}
