package com.jromeo.backend.grocerylist.controller;

import com.jromeo.backend.grocerylist.service.GroceryShoppingListService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/grocery-list")
public class GroceryShoppingListController {

    private final GroceryShoppingListService groceryShoppingListService;

    public GroceryShoppingListController(GroceryShoppingListService groceryShoppingListService) {
        this.groceryShoppingListService = groceryShoppingListService;
    }

    @GetMapping("/generate-list")
    public void generateShoppingList() throws MessagingException {
        groceryShoppingListService.constructEmailWithProvisions();
    }
}
