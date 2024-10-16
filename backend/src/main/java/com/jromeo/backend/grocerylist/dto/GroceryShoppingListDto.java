package com.jromeo.backend.grocerylist.dto;

import com.jromeo.backend.provision.dto.ProvisionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroceryShoppingListDto {
    private List<ProvisionDto> provisionsToShoppingList = new ArrayList<>();
}
