package com.jromeo.backend.shoppinglist.controller;

import com.jromeo.backend.shoppinglist.service.DocumentGeneratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * REST controller responsible for handling API requests related to the shopping list.
 * This controller provides an endpoint to generate a shopping list document using the {@link DocumentGeneratorService}.
 *
 * The generated shopping list is typically in a DOCX format and is created based on the available data.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private final DocumentGeneratorService documentGeneratorService;

    public ShoppingListController(DocumentGeneratorService documentGeneratorService) {
        this.documentGeneratorService = documentGeneratorService;
    }

    /**
     * API endpoint to generate a shopping list document.
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
