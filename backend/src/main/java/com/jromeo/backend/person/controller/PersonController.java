package com.jromeo.backend.person.controller;

import com.jromeo.backend.person.dto.PersonDto;
import com.jromeo.backend.person.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<String> addPersonToHousehold(@Valid @RequestBody PersonDto personDto) {
        personService.addPersonToHousehold(personDto);
        return new ResponseEntity<>("Person added to household!", HttpStatus.CREATED);
    }
}
