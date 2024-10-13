package com.jromeo.backend.recipe;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.recipe.dto.RecipeInstructionDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipePromptBuilder {

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

    public String buildUserPrompt(List<ProvisionDto> provisionDtos) {
        StringBuilder provisions = new StringBuilder("Available provisions: ");
        for (ProvisionDto provision : provisionDtos) {
            provisions.append(provision.getName()).append(", ");
        }

        return provisions.toString();
    }
}
