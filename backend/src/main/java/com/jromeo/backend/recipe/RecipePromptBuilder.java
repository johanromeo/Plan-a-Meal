package com.jromeo.backend.recipe;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.recipe.dto.RecipeInstructionDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Utility class for building prompt strings to be used in requests to the OpenAI API.
 * This class creates prompts based on recipe instructions and available provisions
 * to guide the AI in generating recipe responses in the desired format and language.
 *
 * The {@link RecipePromptBuilder} provides two types of prompts:
 * <p>A system prompt defining the context and response structure for the recipe generation.
 * <p>A user prompt listing the available provisions as inputs for recipe creation.
 *
 * @author Johan Romeo
 */
@Component
public class RecipePromptBuilder {

    /**
     * Builds a system prompt to be used by the AI for generating a recipe.
     * The prompt includes information about the food culture, meal type, time to complete,
     * response language, and the required JSON structure for the AI response.
     *
     * @param instructions the {@link RecipeInstructionDto} containing user instructions for the recipe generation.
     * @return a formatted system prompt string ready to be sent to the AI.
     */
    public String buildSystemPrompt(RecipeInstructionDto instructions) {
        // 1: %s
        String land = instructions.getFoodCulture();
        // 2: %s
        String mealType = instructions.getMealType();
        // 3: %d
        int minutes = instructions.getMinutesToComplete();
        // 4: %s
        String language = instructions.getResponseLanguage();
        String systemPrompt = """
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

        String content = String.format(systemPrompt, land, mealType, minutes, language);

        return content;
    }

    /**
     * Builds a user prompt listing the available provisions for recipe generation.
     * The prompt includes a comma-separated list of provision names to guide the AI in selecting ingredients
     * for the generated recipe.
     *
     * @param provisionDtos a list of {@link ProvisionDto} objects representing the available provisions.
     * @return a string containing the prompt with the list of provisions.
     */
    public String buildUserPrompt(List<ProvisionDto> provisionDtos) {
        StringBuilder provisions = new StringBuilder("Available provisions: ");
        for (ProvisionDto provision : provisionDtos) {
            provisions.append(provision.getName()).append(", ");
        }

        return provisions.toString();
    }
}
