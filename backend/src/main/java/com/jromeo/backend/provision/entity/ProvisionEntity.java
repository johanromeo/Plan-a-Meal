package com.jromeo.backend.provision.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "provisions")
@Getter
@Setter
@NoArgsConstructor
public class ProvisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provision_id")
    private Integer id;
    @Column(name = "provision_name", unique = true)
    private String name;
    @Column(name = "number_of_units")
    private int units;
    @Column(name = "is_in_grocery_shopping_list")
    private boolean addedToGroceryShoppingList;

    public ProvisionEntity(Integer id, String name, int units, boolean addedToGroceryShoppingList) {
        this.id = id;
        this.name = name;
        this.units = units;
        this.addedToGroceryShoppingList = addedToGroceryShoppingList;
    }
}
