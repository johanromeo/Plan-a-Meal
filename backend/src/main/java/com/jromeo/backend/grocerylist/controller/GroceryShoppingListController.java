package com.jromeo.backend.grocerylist.controller;

import com.jromeo.backend.grocerylist.service.GroceryShoppingListService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Grocery shopping list controller.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/grocery-list")
@CrossOrigin("*")
public class GroceryShoppingListController {

    private final GroceryShoppingListService groceryShoppingListService;

    /**
     * Instantiates a new Grocery shopping list controller.
     *
     * @param groceryShoppingListService the grocery shopping list service
     * @author Johan Romeo
     */
    public GroceryShoppingListController(GroceryShoppingListService groceryShoppingListService) {
        this.groceryShoppingListService = groceryShoppingListService;
    }

    /**
     * Generate shopping list.
     *
     * @throws MessagingException the messaging exception
     * @author Johan Romeo
     */
    @PostMapping("/generate-list")
    public void generateShoppingList() throws MessagingException {
        groceryShoppingListService.constructEmailWithProvisions();
    }
}
