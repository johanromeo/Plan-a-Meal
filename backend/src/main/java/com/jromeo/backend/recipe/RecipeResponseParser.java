package com.jromeo.backend.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.recipe.dto.RecipeDto;
import org.springframework.stereotype.Component;

/**
 * Utility class responsible for parsing the response from the OpenAI API.
 * The {@link RecipeResponseParser} extracts the relevant content from the API response
 * and maps it to a {@link RecipeDto} object.
 *
 * This class uses Jackson's {@link ObjectMapper} to parse JSON data from the API response
 * and convert it into a structured {@link RecipeDto} that can be used in the system.
 *
 * @author Johan Romeo
 */
@Component
public class RecipeResponseParser {

    /**
     * Parses the response from the OpenAI API and maps it to a {@link RecipeDto} object.
     *
     * <p>The method navigates through the JSON response structure to extract the content
     * of the recipe, which is provided in a nested structure. The extracted content is
     * then converted to a {@link RecipeDto} using {@link ObjectMapper}.</p>
     *
     * @param response the raw JSON response from the OpenAI API as a string.
     * @return a {@link RecipeDto} object representing the parsed recipe.
     * @throws JsonProcessingException if an error occurs during JSON parsing.
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
