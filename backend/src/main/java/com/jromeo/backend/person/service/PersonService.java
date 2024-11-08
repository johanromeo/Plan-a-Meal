package com.jromeo.backend.person.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.exceptions.IllegalEmailException;
import com.jromeo.backend.person.dto.PersonDto;
import com.jromeo.backend.person.entity.PersonEntity;
import com.jromeo.backend.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Person service.
 *
 * @author Johan Romeo
 */
@Service
public class PersonService {

    private final ObjectMapper objectMapper;
    private final PersonRepository personRepository;

    /**
     * Instantiates a new Person service.
     *
     * @param objectMapper     the object mapper
     * @param personRepository the person repository
     * @author Johan Romeo
     */
    public PersonService(ObjectMapper objectMapper, PersonRepository personRepository) {
        this.objectMapper = objectMapper;
        this.personRepository = personRepository;
    }

    /**
     * Add person to household person dto.
     *
     * @param personDto the person dto
     * @return the person dto
     * @author Johan Romeo
     */
    public PersonDto addPersonToHousehold(PersonDto personDto) {
        if (!personDto.getEmail().contains("@")) {
            throw new IllegalEmailException("Invalid email! Must contain '@'");
        } else {
            PersonEntity personEntity = new PersonEntity(
                    personDto.getId(),
                    personDto.getName(),
                    personDto.getEmail()
            );
            personRepository.save(personEntity);

            return objectMapper.convertValue(personEntity, PersonDto.class);
        }
    }

    public List<PersonDto> getPeople() {
        List<PersonEntity> people = personRepository.findAll();

        return objectMapper.convertValue(people, new TypeReference<>() {});
    }

    /**
     * Get people email addresses string [ ].
     *
     * @return the string [ ]
     * @author Johan Romeo
     */
    public String[] getPeopleEmailAddresses() {
        List<PersonDto> people = objectMapper.convertValue(personRepository.findAll(),
                new TypeReference<>() {}
        );

        String[] emailAddresses = new String[people.size()];

        for (int i = 0; i < emailAddresses.length; i++) {
            emailAddresses[i] = people.get(i).getEmail();
        }

        return emailAddresses;
    }
}
