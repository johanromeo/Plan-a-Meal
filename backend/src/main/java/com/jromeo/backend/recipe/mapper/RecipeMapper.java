package com.jromeo.backend.recipe.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.recipe.dto.RecipeResponseDto;
import com.jromeo.backend.recipe.entity.RecipeEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Recipe mapper.
 *
 * @author Johan Romeo
 */
@Component
public class RecipeMapper {

    private final ObjectMapper objectMapper;

    /**
     * Instantiates a new Recipe mapper.
     *
     * @param objectMapper the object mapper
     * @author Johan Romeo
     */
    public RecipeMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Map to entity recipe entity.
     *
     * @param recipeResponseDto the recipe dto
     * @return the recipe entity
     * @throws JsonProcessingException the json processing exception
     * @author Johan Romeo
     */
    public RecipeEntity mapToEntity(RecipeResponseDto recipeResponseDto) throws JsonProcessingException {
        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setTitle(recipeResponseDto.getTitle());
        String jsonInstructions = objectMapper.writeValueAsString(recipeResponseDto.getInstructions());
        recipeEntity.setInstructions(jsonInstructions);

        return recipeEntity;
    }

    /**
     * Map to dto recipe dto.
     *
     * @param recipeEntity the recipe entity
     * @return the recipe dto
     * @throws IOException the io exception
     * @author Johan Romeo
     */
    public RecipeResponseDto mapToDto(RecipeEntity recipeEntity) throws IOException {
        RecipeResponseDto recipeResponseDto = new RecipeResponseDto();

        recipeResponseDto.setId(recipeEntity.getId());
        recipeResponseDto.setTitle(recipeEntity.getTitle());
        List<String> instructionsToString = objectMapper.readValue(
                recipeEntity.getInstructions(),
                new TypeReference<>() {
                });
        recipeResponseDto.setInstructions(instructionsToString);

        return recipeResponseDto;
    }

    /**
     * Map to dtos list.
     *
     * @param recipeEntities the recipe entities
     * @return the list
     * @throws IOException the io exception
     * @author Johan Romeo
     */
    public List<RecipeResponseDto> mapToDtos(List<RecipeEntity> recipeEntities) throws IOException {
        List<RecipeResponseDto> recipeResponseDtos = new ArrayList<>();

        for (RecipeEntity recipeEntity : recipeEntities) {
            recipeResponseDtos.add(mapToDto(recipeEntity));
        }

        return recipeResponseDtos;
    }
}
