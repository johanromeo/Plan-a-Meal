package com.jromeo.backend.provision.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionDTO {

    private Integer id;
    private String name;
    private int units;
    private boolean addedToGroceryShoppingList;
}
