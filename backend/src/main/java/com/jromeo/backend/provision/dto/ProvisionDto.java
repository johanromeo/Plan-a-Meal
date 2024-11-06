package com.jromeo.backend.provision.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Provision dto.
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
