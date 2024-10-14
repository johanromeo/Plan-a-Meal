package com.jromeo.backend.shoppinglist.dto;

import com.jromeo.backend.provision.dto.ProvisionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing the shopping list.
 * This class holds a list of provisions that are included in the shopping list,
 * represented by {@link ProvisionDto} objects.
 *
 * @author Johan Romeo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListDto {

    private List<ProvisionDto> provisionsToShoppingList = new ArrayList<>();
}
