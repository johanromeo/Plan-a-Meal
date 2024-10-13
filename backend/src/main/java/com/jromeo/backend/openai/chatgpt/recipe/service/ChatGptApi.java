package com.jromeo.backend.openai.chatgpt.recipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jromeo.backend.openai.chatgpt.recipe.RequestBuilderDto;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGptApi {

    @Value("${openai.api-key}")
    private String apiKey;
    @Value("${openai.chatgpt-url}")
    private String chatgptUrl;

    private final RestTemplate restTemplate;
    private final ProvisionService provisionService;

    public ChatGptApi(RestTemplate restTemplate, ProvisionService provisionService) {
        this.restTemplate = restTemplate;
        this.provisionService = provisionService;
    }

    public String callChatGptApi(RequestBuilderDto requestBuilderDto) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<RequestBuilderDto> requestEntity = new HttpEntity<>(requestBuilderDto, headers);
        ResponseEntity<String> response = restTemplate.exchange(chatgptUrl, HttpMethod.POST, requestEntity, String.class);


//        // Should be in a separate parser class for recipes
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode = objectMapper.readTree(response.getBody());
//        String content = rootNode.path("choices").get(0).path("message").path("content").asText();
//        RecipeDto recipeDto = objectMapper.readValue(content, RecipeDto.class);


        // Should be in a separate class - RecipeService for db management
//        recipeRepository.save(recipeMapper.mapToEntity(recipeDto));

        return response.getBody();
    }
}
