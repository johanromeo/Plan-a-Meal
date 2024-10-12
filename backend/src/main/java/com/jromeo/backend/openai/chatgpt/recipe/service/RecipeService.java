package com.jromeo.backend.openai.chatgpt.recipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.openai.chatgpt.recipe.dto.RecipeDto;
import com.jromeo.backend.openai.chatgpt.recipe.dto.RecipeInstructionDto;
import com.jromeo.backend.openai.chatgpt.recipe.dto.RequestBuilderDto;
import com.jromeo.backend.openai.chatgpt.recipe.mapper.RecipeMapper;
import com.jromeo.backend.openai.chatgpt.recipe.repository.RecipeRepository;
import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecipeService {

    @Value("${openai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ProvisionService provisionService;
    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;

    public RecipeService(RestTemplate restTemplate, ProvisionService provisionService, RecipeMapper recipeMapper, RecipeRepository recipeRepository) {
        this.restTemplate = restTemplate;
        this.provisionService = provisionService;
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;
    }

    public RecipeDto generateRecipe(RecipeInstructionDto systemPromptDTO) throws JsonProcessingException {
        final String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<RequestBuilderDto> requestEntity = buildRecipeRequest(systemPromptDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(response.getBody());
        String content = rootNode.path("choices").get(0).path("message").path("content").asText();

        RecipeDto recipeDto = objectMapper.readValue(content, RecipeDto.class);
        recipeRepository.save(recipeMapper.mapToEntity(recipeDto));

        return recipeDto;
    }

    private HttpEntity<RequestBuilderDto> buildRecipeRequest(RecipeInstructionDto systemPrompt, HttpHeaders headers) {
        // 1: %s
        String land = systemPrompt.getFoodCulture();
        // 2: %s
        String mealType = systemPrompt.getMealType();
        // 3: %d
        int minutes = systemPrompt.getMinutesToComplete();
        // 4: %s
        String language = systemPrompt.getResponseLanguage();
        String instructions = """
                You are a master chef from %s.
                You must generate a %s recipe based solely on the user's provided content.
                The recipe should take %d minutes to complete.
                You must answer in %s.
                You must come up with a suitable "title" and list all of the "instructions" chronologically.
                Your response will be in a JSON format structured like this:
                '{
                  "title": "string",
                  "instructions": [
                    "string"
                  ]
                }'
                Do not include any additional text or explanations outside of the JSON structure.
                """;

        String content = String.format(instructions, land, mealType, minutes, language);

        RequestBuilderDto.Message systemMessage = new RequestBuilderDto.Message();
        systemMessage.setRole("system");
        systemMessage.setContent(content);
        RequestBuilderDto.Message userMessage = new RequestBuilderDto.Message();
        userMessage.setRole("user");

        List<ProvisionDto> setProvisionsAsContent = provisionService.findAllPositiveProvisions();
        StringBuilder separatedProvisions = new StringBuilder();
        for (ProvisionDto provisionDTO : setProvisionsAsContent) {
            separatedProvisions.append(provisionDTO.getName()).append(", ");
        }
        userMessage.setContent(separatedProvisions.toString());

        RequestBuilderDto.ResponseFormat responseFormat = new RequestBuilderDto.ResponseFormat();
        responseFormat.setType("json_object");

        RequestBuilderDto builder = RequestBuilderDto.builder()
                .model("gpt-3.5-turbo")
                .message(List.of(systemMessage, userMessage))
                .responseFormat(responseFormat)
                .build();

        return new HttpEntity<>(builder, headers);
    }
}
