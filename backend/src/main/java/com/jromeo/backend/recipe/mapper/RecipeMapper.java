package com.jromeo.backend.recipe.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.recipe.dto.RecipeResponseDto;
import com.jromeo.backend.recipe.entity.RecipeResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for mapping {@link RecipeResponseDto} object(s) to {@link RecipeResponseEntity} and vice versa.
 *
 * @author Johan Romeo
 */
@Component
@RequiredArgsConstructor
public class RecipeMapper {

    private final ObjectMapper objectMapper;

    public RecipeResponseEntity mapToEntity(RecipeResponseDto recipeResponseDto) throws JsonProcessingException {
        RecipeResponseEntity recipeResponseEntity = new RecipeResponseEntity();

        recipeResponseEntity.setTitle(recipeResponseDto.getTitle());

        String jsonInstructions = objectMapper.writeValueAsString(recipeResponseDto.getInstructions());

        recipeResponseEntity.setInstructions(jsonInstructions);

        return recipeResponseEntity;
    }

    public RecipeResponseDto mapToDto(RecipeResponseEntity recipeResponseEntity) throws IOException {
        RecipeResponseDto recipeResponseDto = new RecipeResponseDto();

        recipeResponseDto.setId(recipeResponseEntity.getId());
        recipeResponseDto.setTitle(recipeResponseEntity.getTitle());

        List<String> instructionsToString = objectMapper.readValue(
                recipeResponseEntity.getInstructions(),
                new TypeReference<>() {}
                );

        recipeResponseDto.setInstructions(instructionsToString);

        return recipeResponseDto;
    }

    public List<RecipeResponseDto> mapToDtos(List<RecipeResponseEntity> recipeEntities) throws IOException {
        List<RecipeResponseDto> recipeResponseDtos = new ArrayList<>();

        for (RecipeResponseEntity recipeResponseEntity : recipeEntities) {
            recipeResponseDtos.add(mapToDto(recipeResponseEntity));
        }

        return recipeResponseDtos;
    }
}
