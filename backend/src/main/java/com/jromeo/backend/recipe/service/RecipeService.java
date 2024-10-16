package com.jromeo.backend.recipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jromeo.backend.recipe.dto.RecipeInstructionDto;
import com.jromeo.backend.recipe.RecipePromptBuilder;
import com.jromeo.backend.recipe.RecipeResponseParser;
import com.jromeo.backend.recipe.dto.RecipeDto;
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
        String userAvailableProvisions = promptBuilder.buildUserPrompt(provisionService.findAllPositiveProvisions());
        RequestMessage userMessage = new RequestMessage(
                Role.USER,
                userAvailableProvisions
        );
        // Response as JSON, later to be mapped to RecipeDto
        RequestResponseFormat jsonAsResponseBody = new RequestResponseFormat(
                FormatType.JSON_OBJECT
        );
        // Build the prompt
        RequestBuilder requestBuilderBody = new RequestBuilder(
                ModelType.GPT_3_5_TURBO,
                List.of(systemMessage, userMessage),
                jsonAsResponseBody
        );

        String responseBody = api.callChatGptApi(requestBuilderBody);

        RecipeDto recipeDto = parser.parseResponse(responseBody);
        // Should be in separate service class
        recipeRepository.save(recipeMapper.mapToEntity(recipeDto));

        return recipeDto;
    }
}
