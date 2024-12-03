package com.jromeo.backend.recipe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jromeo.backend.recipe.dto.RecipeResponseDto;
import com.jromeo.backend.recipe.dto.RecipeInstructionDto;
import com.jromeo.backend.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * Controller class for exposing the /recipes endpoints, making it possible to perform
 * POST request related to {@link RecipeInstructionDto} and GET and DELETE requests related to {@link RecipeResponseDto}.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RecipeController {

    private final RecipeService recipeService;

    /**
     * Endpoint for generating a recipe from ChatGPT based on user's requirements.
     *
     * @param recipeInstructionDto the user's requirements.
     * @return a {@link RecipeResponseDto} object as a representation of ChatGPT's response.
     * @throws JsonProcessingException thrown if there are problems with the JSON parsing
     * of {@link RecipeInstructionDto}.
     */
    @PostMapping("/generate-recipe")
    public ResponseEntity<RecipeResponseDto> generateRecipe(@RequestBody RecipeInstructionDto recipeInstructionDto)
            throws JsonProcessingException {
        return new ResponseEntity<>(recipeService.generateRecipe(recipeInstructionDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Integer id) throws IOException {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes() throws IOException {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
