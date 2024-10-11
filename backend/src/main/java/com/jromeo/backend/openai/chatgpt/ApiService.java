package com.jromeo.backend.openai.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.provision.dto.ProvisionDTO;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//TODO: Logger
@Service
public class ApiService {

    @Value("${openai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ProvisionService provisionService;

    public ApiService(RestTemplate restTemplate, ProvisionService provisionService) {
        this.restTemplate = restTemplate;
        this.provisionService = provisionService;
    }

    public Recipe generateRecipe(RecipeSystemPromptDTO systemPromptDTO) throws JsonProcessingException {
        final String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<RequestBuilder> requestEntity = buildRecipeRequest(systemPromptDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(response.getBody());
        String content = rootNode.path("choices").get(0).path("message").path("content").asText();

        return mapper.readValue(content, Recipe.class);
    }

    //TODO:
    private HttpEntity<RequestBuilder> buildRecipeRequest(RecipeSystemPromptDTO systemPrompt, HttpHeaders headers) {
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
                You must write the response in %s.
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

        RequestBuilder.Message systemMessage = new RequestBuilder.Message();
        systemMessage.setRole("system");
        systemMessage.setContent(content);
        RequestBuilder.Message userMessage = new RequestBuilder.Message();
        userMessage.setRole("user");

        List<ProvisionDTO> setProvisionsAsContent = provisionService.findAllPositiveProvisions();
        StringBuilder separatedProvisions = new StringBuilder();
        for (ProvisionDTO provisionDTO : setProvisionsAsContent) {
            separatedProvisions.append(provisionDTO.getName()).append(", ");
        }
        userMessage.setContent(separatedProvisions.toString());

        RequestBuilder.ResponseFormat responseFormat = new RequestBuilder.ResponseFormat();
        responseFormat.setType("json_object");

        RequestBuilder builder = RequestBuilder.builder()
                .model("gpt-3.5-turbo")
                .message(List.of(systemMessage, userMessage))
                .responseFormat(responseFormat)
                .build();

        return new HttpEntity<>(builder, headers);
    }
}
