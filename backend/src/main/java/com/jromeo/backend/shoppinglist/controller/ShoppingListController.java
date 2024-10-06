package com.jromeo.backend.shoppinglist.controller;

import com.jromeo.backend.provision.dto.ProvisionDTO;
import com.jromeo.backend.shoppinglist.service.ShoppingListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/generate-list")
    public List<ProvisionDTO> getShoppingList() {
        return shoppingListService.addProvisionsToGroceryShoppingList();
    }
}
