package com.jromeo.backend.grocerylist.controller;

import com.jromeo.backend.grocerylist.service.GroceryShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class exposes API-endpoint to create a grocery shopping list.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/grocery-list")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GroceryShoppingListController {

    private final GroceryShoppingListService groceryShoppingListService;

    /**
     * Generate a grocery shopping list bast on missing and boolean flagged provisions.
     */
    @PostMapping("/generate-list")
    public void generateShoppingList() {
        groceryShoppingListService.sendEmailWithProvisions();
    }
}
