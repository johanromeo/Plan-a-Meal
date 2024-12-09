package com.jromeo.backend.recipe;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.recipe.dto.RecipeInstructionDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class for providing methods related to prompting ChatGPT for recipes.
 *
 * @author Johan Romeo
 */
@Component
public class RecipePromptBuilder {

    /**
     * Method for instructing ChatGPT how the recipe should be generated, based on the
     * "String systemPrompt" and the user's recipe instructions.
     *
     * @param recipeInstructions the recipe instructions set by the user.
     * @return the String containing the "systemPrompt" and the recipe instructions set by the user.
     */
    public String promptChatGptForRecipe(RecipeInstructionDto recipeInstructions) {
        // From which country or culture do you want your recipe to come from?
        String foodCultureOfChoice = recipeInstructions.getFoodCultureOfChoice();

        // Should it be a recipe of; breakfast, dinner, lunch, after noon snack?
        String mealType = recipeInstructions.getMealType();

        // How long should it take to complete the recipe (in minutes)?
        int maxMinutesToCompleteRecipe = recipeInstructions.getMaxMinutesToCompleteRecipe();

        // In what language do you want the chatbot's response?
        String chatBotTextLanguage = recipeInstructions.getChatBotTextLanguage();

        // Pre-configured instructions to ChatGPT to minimize hallucinations.
        String systemPrompt = """
                You are a master chef from %s.
                You know what provisions can be mixed and not mixed.
                You must generate a %s recipe based solely on the user's provided "Available provisions"-content.
                The recipe must only contain a combination of the provided provisions that mix well together and are
                well known. You must not generate a recipe like "Oatmeal and Pasta" or Honey with Pasta".
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

        return String.format(systemPrompt, foodCultureOfChoice, mealType, maxMinutesToCompleteRecipe, chatBotTextLanguage);
    }

    /**
     * Method's sole purpose is to provide ChatGPT with the household's available provisions, making
     * it possible to generate a recipe based on them.
     *
     * @param provisionDtos the list of available provisions.
     * @return a toString() of the StringBuilder object containing the available provisions.
     */
    public String loadHouseholdAvailableProvisions(List<ProvisionDto> provisionDtos) {
        StringBuilder userAvailableProvisions = new StringBuilder("Available provisions: ");

        for (ProvisionDto provision : provisionDtos) {
            userAvailableProvisions.append(provision.getName()).append(", ");
        }

        return userAvailableProvisions.toString();
    }
}
