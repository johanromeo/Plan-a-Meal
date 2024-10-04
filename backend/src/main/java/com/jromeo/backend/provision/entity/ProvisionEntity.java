package com.jromeo.backend.provision.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "provisions")
public class ProvisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provision_id")
    private Integer id;
    @Column(name = "provision_name")
    private String name;
    @Column(name = "number_of_units")
    private int units;
    @Column(name = "is_in_grocery_shopping_list")
    private boolean addedToGroceryShoppingList;

    public ProvisionEntity() {

    }

    public ProvisionEntity(String name, int units, boolean addedToGroceryShoppingList) {
        this.name = name;
        this.units = units;
        this.addedToGroceryShoppingList = addedToGroceryShoppingList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public boolean isAddedToGroceryShoppingList() {
        return addedToGroceryShoppingList;
    }

    public void setAddedToGroceryShoppingList(boolean addedToGroceryShoppingList) {
        this.addedToGroceryShoppingList = addedToGroceryShoppingList;
    }
}
