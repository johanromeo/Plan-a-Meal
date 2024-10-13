package com.jromeo.backend.recipe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jromeo.backend.recipe.dto.RecipeInstructionDto;
import com.jromeo.backend.recipe.service.RecipeService;
import com.jromeo.backend.recipe.dto.RecipeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<RecipeDto> getRecipe(@RequestBody RecipeInstructionDto recipeInstructionDto)
            throws JsonProcessingException {
        return new ResponseEntity<>(recipeService.generateRecipe(recipeInstructionDto), HttpStatus.OK);
    }
}
