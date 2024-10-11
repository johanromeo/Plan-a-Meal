package com.jromeo.backend.openai.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChatGptService {

    @Value("${openai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ChatGptService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Recipe makeRequest() throws JsonProcessingException {
        final String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        ChatGptRequestBuilder.Message systemMessage = new ChatGptRequestBuilder.Message();
        systemMessage.setRole("system");
        systemMessage.setContent("""
                You are a master chef, a food expert and will answer in swedish.
                Based on the user's content, generate a breakfast recipe that takes no longer than 10 minutes to complete
                and list all the steps chronologically.
                Your respond will be in a JSON format structured like this:
                '{
                  "title": "string",
                  "instructions": [
                    "string"
                  ]
                }'
                """);
        ChatGptRequestBuilder.Message userMessage = new ChatGptRequestBuilder.Message();
        userMessage.setRole("user");
        userMessage.setContent("Mjölk, Spenat, Morot, Mjöl, Bröd");

        ChatGptRequestBuilder.ResponseFormat responseFormat = new ChatGptRequestBuilder.ResponseFormat();
        responseFormat.setType("json_object");



        ChatGptRequestBuilder builder = ChatGptRequestBuilder.builder()
                .model("gpt-3.5-turbo")
                .message(List.of(systemMessage, userMessage))
                .responseFormat(responseFormat)
                .build();

        HttpEntity<ChatGptRequestBuilder> requestEntity = new HttpEntity<>(builder, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        ObjectMapper mapper = new ObjectMapper();

        // Parse the response to extract the content field from the first choice
        JsonNode rootNode = mapper.readTree(response.getBody());
        String content = rootNode.path("choices").get(0).path("message").path("content").asText();

        // Now map the content (which is the JSON string for Recipe) to the Recipe class
        Recipe recipe = mapper.readValue(content, Recipe.class);

        return recipe;

    }
}
