package com.jromeo.backend.openai.chatgpt.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jromeo.backend.openai.chatgpt.recipe.dto.RecipeDto;
import com.jromeo.backend.openai.chatgpt.recipe.mapper.RecipeMapper;
import com.jromeo.backend.openai.chatgpt.recipe.repository.RecipeRepository;
import com.jromeo.backend.openai.chatgpt.recipe.service.ChatGptApi;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
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
    private final ProvisionMapper mapper;
    private final RecipeMapper recipeMapper;

    public RecipeService(ChatGptApi api, RecipePromptBuilder promptBuilder, RecipeResponseParser parser, ProvisionService provisionService, RecipeRepository recipeRepository, ProvisionMapper mapper, RecipeMapper recipeMapper) {
        this.api = api;
        this.promptBuilder = promptBuilder;
        this.parser = parser;
        this.provisionService = provisionService;
        this.recipeRepository = recipeRepository;
        this.mapper = mapper;
        this.recipeMapper = recipeMapper;
    }

    public RecipeDto generateRecipe(RecipeInstructionDto instructions) throws JsonProcessingException {
        // Feed system prompt
        String systemPrompt = promptBuilder.buildSystemPrompt(instructions);

        RequestBuilderDto.Message systemMessage = new RequestBuilderDto.Message();
        systemMessage.setRole("system");
        systemMessage.setContent(systemPrompt);

        // Feed user prompt
        String provs = promptBuilder.buildUserPrompt(provisionService.findAllPositiveProvisions());

        RequestBuilderDto.Message userMessage = new RequestBuilderDto.Message();
        userMessage.setRole("user");
        userMessage.setContent(provs);

        // Set response_format
        RequestBuilderDto.ResponseFormat responseFormat = new RequestBuilderDto.ResponseFormat();
        responseFormat.setType("json_object");

        // Build the prompt
        RequestBuilderDto requestBody = RequestBuilderDto.builder()
                .model("gpt-3.5-turbo")
                .message(List.of(systemMessage, userMessage))
                .responseFormat(responseFormat)
                .build();

        // Call API
        String responseBody = api.callChatGptApi(requestBody);

        // Parse API response
        RecipeDto recipeDto = parser.parseResponse(responseBody);


        // Should be in separate service class
        recipeRepository.save(recipeMapper.mapToEntity(recipeDto));
//
        return recipeDto;
    }
}
