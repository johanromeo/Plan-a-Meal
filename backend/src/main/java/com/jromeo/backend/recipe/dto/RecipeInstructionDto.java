package com.jromeo.backend.openai.chatgpt.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeInstructionDto {
    private String foodCulture; //e.g. Swedish, Italian
    private String responseLanguage; //e.g. Swedish, English
    private String mealType; //e.g. Breakfast, Lunch, Dinner
    private int minutesToComplete; //e.g. 15 minutes, 45 minutes, 90 minutes
}
