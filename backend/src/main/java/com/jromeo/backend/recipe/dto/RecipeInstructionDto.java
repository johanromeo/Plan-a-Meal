package com.jromeo.backend.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO class for representing the recipe instructions given by the user when prompting ChatGPT.
 *
 * @author Johan Romeo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeInstructionDto {

    private String foodCultureOfChoice; //e.g. Swedish, Italian

    private String chatBotTextLanguage; //e.g. Swedish, English

    private String mealType; //e.g. Breakfast, Lunch, Dinner

    private int maxMinutesToCompleteRecipe; //e.g. 15 minutes, 45 minutes, 90 minutes
}
