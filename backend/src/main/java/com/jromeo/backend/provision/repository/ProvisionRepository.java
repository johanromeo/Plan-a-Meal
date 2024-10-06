package com.jromeo.backend.provision.repository;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvisionRepository extends JpaRepository<ProvisionEntity, Integer> {

    @Query(
            value = "SELECT * FROM provisions " +
                    "WHERE number_of_units = 0 " +
                    "AND in_grocery_shopping_list = true",
            nativeQuery = true
    )
    List<ProvisionEntity> addProvisionToShoppingList();

    ProvisionEntity findByName(String name);

}
