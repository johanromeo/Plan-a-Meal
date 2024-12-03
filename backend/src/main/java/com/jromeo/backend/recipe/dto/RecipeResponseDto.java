package com.jromeo.backend.recipe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jromeo.backend.recipe.entity.RecipeResponseEntity;
import lombok.Data;

import java.util.List;

/**
 * A DTO class representing {@link RecipeResponseEntity}.
 *
 * @author Johan Romeo
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeResponseDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("instructions")
    private List<String> instructions;
}
