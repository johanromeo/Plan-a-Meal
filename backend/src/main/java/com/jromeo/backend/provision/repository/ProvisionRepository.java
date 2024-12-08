package com.jromeo.backend.provision.repository;

import com.jromeo.backend.grocerylist.service.GroceryShoppingListService;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.recipe.service.RecipeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvisionRepository extends JpaRepository<ProvisionEntity, Integer> {


    @Query(value =
            "SELECT * FROM provisions " +
            "ORDER BY name DESC "
            , nativeQuery = true)
    List<ProvisionEntity> findAllBySortedDescName();

    /**
     * Custom query to find all provisions that meet the requirements to be added in the grocery shopping list.
     * Used by {@link GroceryShoppingListService} to construct an email of missing provisions.
     *
     * @return the list of provisions.
     */
    @Query(value =
            "SELECT * FROM provisions " +
            "WHERE number_of_units = 0 " +
            "AND in_grocery_shopping_list = true",
            nativeQuery = true
    )
    List<ProvisionEntity> findAllProvisionsToBeAddedToGroceryShoppingList();

    /**
     * Custom query to find all available provisions in the household.
     * Used by {@link RecipeService} to give provision data when prompting for a generated recipe.
     *
     * @return the list of available provisions.
     */
    @Query(value =
            "SELECT * FROM provisions " +
            "WHERE number_of_units > 0",
            nativeQuery = true)
    List<ProvisionEntity> findAllAvailableProvisionsInHousehold();

}
