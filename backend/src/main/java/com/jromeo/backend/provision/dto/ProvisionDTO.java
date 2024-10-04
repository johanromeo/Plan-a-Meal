package com.jromeo.backend.provision.dto;

public class ProvisionDTO {

    private String name;
    private int units;
    private boolean addedToGroceryShoppingList;

    public ProvisionDTO() {
    }

    public ProvisionDTO(String name, int units, boolean addedToGroceryShoppingList) {
        this.name = name;
        this.units = units;
        this.addedToGroceryShoppingList = addedToGroceryShoppingList;
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
