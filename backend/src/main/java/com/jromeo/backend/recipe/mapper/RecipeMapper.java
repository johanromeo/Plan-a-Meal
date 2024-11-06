package com.jromeo.backend.recipe.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.recipe.dto.RecipeDto;
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
     * @param recipeDto the recipe dto
     * @return the recipe entity
     * @throws JsonProcessingException the json processing exception
     * @author Johan Romeo
     */
    public RecipeEntity mapToEntity(RecipeDto recipeDto) throws JsonProcessingException {
        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setTitle(recipeDto.getTitle());
        String jsonInstructions = objectMapper.writeValueAsString(recipeDto.getInstructions());
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

    /**
     * Map to dtos list.
     *
     * @param recipeEntities the recipe entities
     * @return the list
     * @throws IOException the io exception
     * @author Johan Romeo
     */
    public List<RecipeDto> mapToDtos(List<RecipeEntity> recipeEntities) throws IOException {
        List<RecipeDto> recipeDtos = new ArrayList<>();

        for (RecipeEntity recipeEntity : recipeEntities) {
            recipeDtos.add(mapToDto(recipeEntity));
        }

        return recipeDtos;
    }
}
