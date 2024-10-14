package com.jromeo.backend.recipe.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.recipe.dto.RecipeDto;
import com.jromeo.backend.recipe.entity.RecipeEntity;
import com.jromeo.backend.recipe.service.RecipeService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Mapper class using {@link ObjectMapper} to map recipes from DTO to Entity and Entity to DTO.
 * Used by {@link RecipeService}.
 *
 * @author Johan Romeo
 */
@Component
public class RecipeMapper {

    private final ObjectMapper objectMapper;

    public RecipeMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Maps a {@link RecipeDto} object to a {@link RecipeEntity} object.
     * This method uses {@link ObjectMapper} to convert the instructions field of the {@link RecipeDto}
     * to its JSON string representation before setting it in the {@link RecipeEntity}.
     *
     * @param recipeDto the {@link RecipeDto} object to be mapped.
     * @return a {@link RecipeEntity} object that represents the mapped entity from the provided DTO.
     * @throws JsonProcessingException if an error occurs while converting the instructions field to JSON format.
     */
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
