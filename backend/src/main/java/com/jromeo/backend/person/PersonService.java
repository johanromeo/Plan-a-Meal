package com.jromeo.backend.person;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jromeo.backend.exceptions.IllegalEmailException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final ObjectMapper objectMapper;
    private final PersonRepository personRepository;

    public PersonService(ObjectMapper objectMapper, PersonRepository personRepository) {
        this.objectMapper = objectMapper;
        this.personRepository = personRepository;
    }

    public void addPersonToHousehold(PersonDto personDto) {
        if (personDto.getEmail().contains("@")) {
            PersonEntity personEntity = new PersonEntity(
                    personDto.getId(),
                    personDto.getName(),
                    personDto.getEmail()
            );
            personRepository.save(personEntity);

        } else {
            throw new IllegalEmailException("Invalid email! Must contain '@'");
        }
    }

    public String[] getPeopleEmailAddresses() {
        List<PersonDto> people = objectMapper.convertValue(personRepository.findAll(),
                new TypeReference<>(){}
        );

        String[] emailAddresses = new String[people.size()];

        for (int i = 0; i < emailAddresses.length; i++) {
            emailAddresses[i] = people.get(i).getEmail();
        }

        return emailAddresses;
    }
}
