package com.jromeo.backend.shoppinglist.dto;

import com.jromeo.backend.provision.dto.ProvisionDTO;
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
public class ShoppingListDTO {

    private List<ProvisionDTO> provisionsToShoppingList = new ArrayList<>();
}
