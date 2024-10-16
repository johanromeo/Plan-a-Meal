package com.jromeo.backend.recipe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("instructions")
    private List<String> instructions;
}
