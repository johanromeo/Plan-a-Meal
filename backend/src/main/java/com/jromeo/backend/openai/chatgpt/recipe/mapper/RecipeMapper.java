package com.jromeo.backend.openai.chatgpt.recipe.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.openai.chatgpt.recipe.dto.RecipeDto;
import com.jromeo.backend.openai.chatgpt.recipe.entity.RecipeEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class RecipeMapper {

    private final ObjectMapper objectMapper;

    public RecipeMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public RecipeEntity mapToEntity(RecipeDto recipeDto) throws JsonProcessingException {
        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setTitle(recipeDto.getTitle());
        String jsonInstructions = objectMapper.writeValueAsString(recipeDto.getInstructions());
        recipeEntity.setInstructions(jsonInstructions);

        return recipeEntity;
    }

    public RecipeDto mapToDto(RecipeEntity recipeEntity) throws IOException {
        RecipeDto recipeDto = new RecipeDto();

        recipeDto.setTitle(recipeEntity.getTitle());
        List<String> instructionsToString = objectMapper.readValue(
                recipeEntity.getInstructions(),
                new TypeReference<>() {
                });
        recipeDto.setInstructions(instructionsToString);

        return recipeDto;

    }
}
