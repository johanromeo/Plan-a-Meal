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
    @Value("${openai.chatgpt-url}")
    private String chatgptUrl;

    private final RestTemplate restTemplate;
    private final ProvisionService provisionService;

    public RecipeService(RestTemplate restTemplate, ProvisionService provisionService) {
        this.restTemplate = restTemplate;
        this.provisionService = provisionService;
    }

    // Method should be renamed
    // Return of type String instead of RecipeDto
    // Take a request body instead
    public String callChatGptApi(RequestBuilderDto requestBuilderDto) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<RequestBuilderDto> requestEntity = new HttpEntity<>(requestBuilderDto, headers);
        ResponseEntity<String> response = restTemplate.exchange(chatgptUrl, HttpMethod.POST, requestEntity, String.class);


        // Should be in a separate parser class for recipes
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        String content = rootNode.path("choices").get(0).path("message").path("content").asText();
        RecipeDto recipeDto = objectMapper.readValue(content, RecipeDto.class);


        // Should be in a separate class - RecipeService for db management
        recipeRepository.save(recipeMapper.mapToEntity(recipeDto));

        return response.getBody();
    }

    private HttpEntity<RequestBuilderDto> buildRecipeRequest(RecipeInstructionDto systemPrompt, HttpHeaders headers) {
        // This is the system prompt and should be separated
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


        // This is the user prompt and should be separated
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
