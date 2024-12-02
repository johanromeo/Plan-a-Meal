package com.jromeo.backend.person.controller;

import com.jromeo.backend.person.dto.PersonDto;
import com.jromeo.backend.person.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for exposing the /household-people endpoint, making ut possible to perform
 * POST, GET, PUT and DELETE request related to {@link PersonDto} object(s).
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/household-people")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> addPersonToHousehold(@Valid @RequestBody PersonDto personDto) {
        personService.addPersonToHousehold(personDto);
        return new ResponseEntity<>(personDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getPeople() {
        List<PersonDto> people = personService.getPeople();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Integer id, @RequestBody PersonDto personDto) {
        return new ResponseEntity<>(personService.updatePerson(id, personDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
