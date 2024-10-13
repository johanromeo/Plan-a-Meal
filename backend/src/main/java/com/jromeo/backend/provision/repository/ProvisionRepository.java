package com.jromeo.backend.provision.repository;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.service.ProvisionService;
import com.jromeo.backend.shoppinglist.service.ShoppingListService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface responsible for providing CRUD methods on {@link ProvisionEntity} in MySQL database.<br>
 * Implemented by {@link ProvisionService} and {@link ShoppingListService}
 *
 * @author Johan Romeo
 */
@Repository
public interface ProvisionRepository extends JpaRepository<ProvisionEntity, Integer> {

    /**
     * Find a {@link ProvisionEntity} by its name.
     * @param name the name of the {@link ProvisionEntity}.
     * @return a {@link ProvisionEntity}.
     */
    ProvisionEntity findByName(String name);

    /**
     * Custom query to find all provisions that meet the requirements to be added to the grocery shopping list.
     * @return a List of {@link ProvisionEntity} objects.
     */
    @Query(value =
            "SELECT * FROM provisions " +
            "WHERE number_of_units = 0 " +
            "AND in_grocery_shopping_list = true",
            nativeQuery = true
    )
    List<ProvisionEntity> addProvisionToShoppingList();


    /**
     * Custom query to find all available provisions when generating a recipe by OpenAI's chat completion.
     * @return a List of {@link ProvisionEntity} objects.
     */
    @Query(value =
            "SELECT * FROM provisions " +
            "WHERE number_of_units > 0",
            nativeQuery = true)
    List<ProvisionEntity> findAllPositiveProvisions();

}
