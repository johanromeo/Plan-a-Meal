package com.jromeo.backend.openai.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeSystemPromptDTO {
    private String foodCulture;
    private String responseLanguage;
    private String dietPreference;
    private String mealType;
    private int minutesToComplete;
}
