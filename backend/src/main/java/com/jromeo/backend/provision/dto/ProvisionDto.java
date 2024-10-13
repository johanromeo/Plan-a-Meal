package com.jromeo.backend.provision.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to represent a Provision Data Transfer Object
 *
 * @author Johan Romeo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionDto {

    private Integer id;
    private String name;
    private int units;
    private boolean addedToShoppingList;
}
