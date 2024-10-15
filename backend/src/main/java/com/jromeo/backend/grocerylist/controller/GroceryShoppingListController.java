package com.jromeo.backend.grocerylist.controller;

import com.jromeo.backend.grocerylist.service.DocumentGeneratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * REST controller responsible for handling API requests related to the grocery shopping list.
 * This controller provides an endpoint to generate a grocery shopping list document using
 * the {@link DocumentGeneratorService}.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/grocery-list")
public class GroceryShoppingListController {

    private final DocumentGeneratorService documentGeneratorService;

    public GroceryShoppingListController(DocumentGeneratorService documentGeneratorService) {
        this.documentGeneratorService = documentGeneratorService;
    }

    /**
     * API endpoint to generate a grocery shopping list document.
     *
     * <p>This method triggers the generation of a shopping list in DOCX format by calling the
     * {@link DocumentGeneratorService#createShoppingListDocx()} method.</p>
     *
     * @throws IOException if an error occurs while generating the document.
     */
    @GetMapping("/generate-list")
    public void generateShoppingList() throws IOException {
        documentGeneratorService.createShoppingListDocx();
    }
}
