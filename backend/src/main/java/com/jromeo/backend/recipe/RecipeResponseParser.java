package com.jromeo.backend.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.recipe.dto.RecipeDto;
import org.springframework.stereotype.Component;

/**
 * The type Recipe response parser.
 *
 * @author Johan Romeo
 */
@Component
public class RecipeResponseParser {


    /**
     * Parse response recipe dto.
     *
     * @param response the response
     * @return the recipe dto
     * @throws JsonProcessingException the json processing exception
     * @author Johan Romeo
     */
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
