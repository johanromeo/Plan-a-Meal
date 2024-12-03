package com.jromeo.backend.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.recipe.dto.RecipeResponseDto;
import org.springframework.stereotype.Component;

/**
 * Parser class for filtering what's relevant in the response from ChatGPT when generating a recipe.
 *
 * @author Johan Romeo
 */
@Component
public class RecipeResponseParser {

    /**
     * Method for filtering the relevant data from ChatGPT's recipe response and
     * mapping it to a {@link RecipeResponseDto} object.
     *
     * @param chatGptResponse the JSON response from generating a recipe with ChatGPT.
     * @return the chatGptResponse converted to a {@link RecipeResponseDto} object.
     * @throws JsonProcessingException the json processing exception
     */
    public RecipeResponseDto parseChatGptResponse(String chatGptResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(chatGptResponse);

        String content = rootNode
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

        return objectMapper.readValue(content, RecipeResponseDto.class);
    }
}
