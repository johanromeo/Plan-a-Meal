package com.jromeo.backend.person.dto;


import com.jromeo.backend.person.entity.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO class of {@link PersonEntity}.
 *
 * @author Johan Romeo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private Integer id;

    private String name;

    private String email;
}
