package com.jromeo.backend.person.controller;

import com.jromeo.backend.person.dto.PersonDto;
import com.jromeo.backend.person.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Person controller.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/household-people")
@CrossOrigin("*")
public class PersonController {

    private final PersonService personService;

    /**
     * Instantiates a new Person controller.
     *
     * @param personService the person service
     * @author Johan Romeo
     */
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Add person to household response entity.
     *
     * @param personDto the person dto
     * @return the response entity
     * @author Johan Romeo
     */
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
