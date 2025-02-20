package com.jromeo.backend.person.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An Entity for representing a Person in the database.
 *
 * @author Johan Romeo
 */
@Table(name = "people_in_houshold")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email_address", unique = true)
    private String email;
}
