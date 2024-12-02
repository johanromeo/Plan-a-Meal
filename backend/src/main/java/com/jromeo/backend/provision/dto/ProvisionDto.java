package com.jromeo.backend.provision.dto;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO class of {@link ProvisionEntity}
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

    private boolean addedToGroceryShoppingList;

    private String imgUrl;
}
