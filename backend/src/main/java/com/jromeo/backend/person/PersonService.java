package com.jromeo.backend.person;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        PersonEntity personEntity = new PersonEntity(
                personDto.getId(),
                personDto.getName(),
                personDto.getEmail()
        );
        personRepository.save(personEntity);
    }

    public String[] getHouseholdEmailAddresses() {
        List<PersonDto> peopleEmailAddresses = objectMapper.convertValue(personRepository.findAll(),
                new TypeReference<>() {
                });

        String[] mailAddresses = new String[peopleEmailAddresses.size()];

        for (int i = 0; i < mailAddresses.length; i++) {
            mailAddresses[i] = peopleEmailAddresses.get(i).getEmail();
        }

        return mailAddresses;
    }
}
