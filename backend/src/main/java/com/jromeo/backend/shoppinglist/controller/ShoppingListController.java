package com.jromeo.backend.shoppinglist.controller;

import com.jromeo.backend.shoppinglist.service.DocumentGeneratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private final DocumentGeneratorService documentGeneratorService;

    public ShoppingListController(DocumentGeneratorService documentGeneratorService) {
        this.documentGeneratorService = documentGeneratorService;
    }

    @GetMapping("/generate-list")
    public void generateShoppingList() throws IOException {
        documentGeneratorService.createShoppingListDocx();
    }
}
