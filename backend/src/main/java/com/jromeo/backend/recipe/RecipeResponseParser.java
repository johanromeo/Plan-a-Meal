package com.jromeo.backend.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.recipe.dto.RecipeDto;
import org.springframework.stereotype.Component;

@Component
public class RecipeResponseParser {

    public RecipeDto parseResponse(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);
        String content = rootNode
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();
        RecipeDto recipeDto = objectMapper.readValue(content, RecipeDto.class);

        return recipeDto;
    }
}
