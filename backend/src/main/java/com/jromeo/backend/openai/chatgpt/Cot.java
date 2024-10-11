package com.jromeo.backend.openai.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class Cot {

    private final ApiService apiService;

    public Cot(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public ResponseEntity<Recipe> getRecipe(@RequestBody RecipeSystemPromptDTO recipeSystemPromptDTO)
            throws JsonProcessingException {
        return new ResponseEntity<>(apiService.generateRecipe(recipeSystemPromptDTO), HttpStatus.OK);
    }
}
