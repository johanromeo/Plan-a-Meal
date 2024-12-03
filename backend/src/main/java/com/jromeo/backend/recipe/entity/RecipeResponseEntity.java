package com.jromeo.backend.recipe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An Entity representing ChatGPT's recipe response in the database.
 *
 * @author Johan Romeo
 */
@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;
}
