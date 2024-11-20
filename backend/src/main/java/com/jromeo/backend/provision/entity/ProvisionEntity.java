package com.jromeo.backend.provision.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Provision entity.
 *
 * @author Johan Romeo
 */
@Entity
@Table(name = "provisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "provision_name", unique = true)
    private String name;
    @Column(name = "number_of_units")
    private int units;
    @Column(name = "in_grocery_shopping_list")
    private boolean addedToGroceryShoppingList;
    @Column(name = "img_url")
    private String imgUrl;
}
