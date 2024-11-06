package com.jromeo.backend.recipe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jromeo.backend.recipe.dto.RecipeDto;
import com.jromeo.backend.recipe.dto.RecipeInstructionDto;
import com.jromeo.backend.recipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * The type Recipe controller.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    /**
     * Instantiates a new Recipe controller.
     *
     * @param recipeService the recipe service
     * @author Johan Romeo
     */
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Generate recipe response entity.
     *
     * @param recipeInstructionDto the recipe instruction dto
     * @return the response entity
     * @throws JsonProcessingException the json processing exception
     * @author Johan Romeo
     */
    @GetMapping("/generate-recipe")
    public ResponseEntity<RecipeDto> generateRecipe(@RequestBody RecipeInstructionDto recipeInstructionDto)
            throws JsonProcessingException {
        return new ResponseEntity<>(recipeService.generateRecipe(recipeInstructionDto), HttpStatus.OK);
    }

    /**
     * Gets recipe by id.
     *
     * @param id the id
     * @return the recipe by id
     * @throws IOException the io exception
     * @author Johan Romeo
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable int id) throws IOException {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    /**
     * Gets all recipes.
     *
     * @return the all recipes
     * @throws IOException the io exception
     * @author Johan Romeo
     */
    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipes() throws IOException {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    /**
     * Delete recipe response entity.
     *
     * @param id the id
     * @return the response entity
     * @author Johan Romeo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
