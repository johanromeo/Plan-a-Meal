package com.jromeo.backend.provision.repository;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Provision repository.
 *
 * @author Johan Romeo
 */
public interface ProvisionRepository extends JpaRepository<ProvisionEntity, Integer> {

    /**
     * Add provision to shopping list.
     *
     * @return the list
     * @author Johan Romeo
     */
    @Query(value =
            "SELECT * FROM provisions " +
            "WHERE number_of_units = 0 " +
            "AND in_grocery_shopping_list = true",
            nativeQuery = true
    )
    List<ProvisionEntity> addProvisionToShoppingList();

    /**
     * Find all positive provisions list.
     *
     * @return the list
     * @author Johan Romeo
     */
    @Query(value =
            "SELECT * FROM provisions " +
            "WHERE number_of_units > 0",
            nativeQuery = true)
    List<ProvisionEntity> findAllPositiveProvisions();

}
