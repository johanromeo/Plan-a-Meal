package com.jromeo.backend.grocerylist.controller;

import com.jromeo.backend.grocerylist.service.GroceryShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for exposing the /grocery-list endpoint, making ut possible to perform
 * POST request to generate a grocery shopping list.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/api/grocery-list")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GroceryShoppingListController {

    private final GroceryShoppingListService groceryShoppingListService;

    /**
     * Lets the user receive an email with what provisions to buy.
     */
    @PostMapping("/generate-list")
    public void generateShoppingList() {
        groceryShoppingListService.sendEmailWithProvisions();
    }
}
