package com.jromeo.backend.person.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.exceptions.IllegalEmailException;
import com.jromeo.backend.exceptions.PersonNotFoundException;
import com.jromeo.backend.person.dto.PersonDto;
import com.jromeo.backend.person.entity.PersonEntity;
import com.jromeo.backend.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for performing CRUD operations on {@link PersonDto} object(s).
 *
 * @author Johan Romeo
 */
@Service
@RequiredArgsConstructor
public class PersonService {

    private final ObjectMapper objectMapper;
    private final PersonRepository personRepository;


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

    public PersonDto updatePerson(Integer id, PersonDto personDto) {
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow( () -> new PersonNotFoundException("Person with id: " + id + " doesn't exist."));

        personEntity.setName(personDto.getName());
        personEntity.setEmail(personDto.getEmail());

        personRepository.save(personEntity);

        return objectMapper.convertValue(personEntity, PersonDto.class);
    }

    public void deletePerson(Integer id) {
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow( ()-> new PersonNotFoundException("Person with id: " + id + " doesn't exist."));

        personRepository.delete(personEntity);
    }

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
