package com.jromeo.backend.shoppinglist.controller;

import com.jromeo.backend.shoppinglist.dto.ShoppingListDTO;
import com.jromeo.backend.shoppinglist.service.ShoppingListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/generate-list")
    public ShoppingListDTO getShoppingList() {
        return shoppingListService.addProvisionsToGroceryShoppingList();
    }
}
