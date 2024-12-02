package com.jromeo.backend.grocerylist.controller;

import com.jromeo.backend.grocerylist.service.GroceryShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for exposing the /grocery-list endpoint, making ut possible to perform
 * POST request to generate grocery shopping lists.
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
     * Generate a grocery shopping list with provisions to buy.
     */
    @PostMapping("/generate-list")
    public void generateShoppingList() {
        groceryShoppingListService.sendEmailWithProvisions();
    }
}
