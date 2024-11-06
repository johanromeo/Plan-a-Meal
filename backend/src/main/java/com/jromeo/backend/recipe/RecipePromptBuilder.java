package com.jromeo.backend.recipe;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.recipe.dto.RecipeInstructionDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type Recipe prompt builder.
 *
 * @author Johan Romeo
 */
@Component
public class RecipePromptBuilder {

    /**
     * Build system prompt string.
     *
     * @param recipeInstructions the recipe instructions
     * @return the string
     * @author Johan Romeo
     */
    public String buildSystemPrompt(RecipeInstructionDto recipeInstructions) {
        // From which country or culture do you want your recipe to come from?
        String foodCultureOfChoice = recipeInstructions.getFoodCultureOfChoice();
        // Should it be a recipe of; breakfast, dinner, lunch, after noon snack?
        String mealType = recipeInstructions.getMealType();
        // How long should it take to complete the recipe (in minutes)?
        int maxMinutesToCompleteRecipe = recipeInstructions.getMaxMinutesToCompleteRecipe();
        // In what language do you want the chatbot's response?
        String chatBotTextLanguage = recipeInstructions.getChatBotTextLanguage();
        String prompt = """
                You are a master chef from %s.
                You must generate a %s recipe based solely on the user's provided "Available provisions"-content.
                The recipe should take %d minutes to complete.
                You must answer in %s.
                You must come up with a suitable "title" and list all of the "instructions" chronologically.
                Your response will be in a JSON format structured like this:
                '{
                  "title": "string",
                  "instructions": [
                    "string"
                  ]
                }'
                Do not include any additional text or explanations outside of the JSON structure.
                """;

        String promptContent = String.format(prompt, foodCultureOfChoice, mealType, maxMinutesToCompleteRecipe, chatBotTextLanguage);

        return promptContent;
    }

    /**
     * Build user prompt string.
     *
     * @param provisionDtos the provision dtos
     * @return the string
     * @author Johan Romeo
     */
    public String buildUserPrompt(List<ProvisionDto> provisionDtos) {
        StringBuilder userAvailableProvisions = new StringBuilder("Available provisions: ");
        for (ProvisionDto provision : provisionDtos) {
            userAvailableProvisions.append(provision.getName()).append(", ");
        }

        return userAvailableProvisions.toString();
    }
}
