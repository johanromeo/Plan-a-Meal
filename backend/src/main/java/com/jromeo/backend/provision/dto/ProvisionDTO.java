package com.jromeo.backend.provision.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProvisionDTO {

    private Integer id;
    private String name;
    private int units;
    private boolean addedToGroceryShoppingList;

    public ProvisionDTO(Integer id,String name, int units, boolean addedToGroceryShoppingList) {
        this.id = id;
        this.name = name;
        this.units = units;
        this.addedToGroceryShoppingList = addedToGroceryShoppingList;
    }
}
