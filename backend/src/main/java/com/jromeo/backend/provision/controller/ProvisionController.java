package com.jromeo.backend.provision.controller;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.service.ProvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for exposing the /provisions endpoints, making it possible to perform
 * POST, GET, PUT, PATCH and DELETE requests related to {@link ProvisionDto} object(s).
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/api/provisions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProvisionController {

    private final ProvisionService provisionService;

    @PostMapping
    public ResponseEntity<String> addProvision(@RequestBody ProvisionDto provisionDTO) {
        provisionService.addProvision(provisionDTO);
        return new ResponseEntity<>("Provision added", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvisionDto> findProvisionById(@PathVariable int id) {
        return new ResponseEntity<>(provisionService.findProvisionById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProvisionDto>> findAllProvisions() {
        return new ResponseEntity<>(provisionService.findAllProvisions(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvisionDto> updateProvision(@PathVariable int id, @RequestBody ProvisionDto provisionDTO) {
        return new ResponseEntity<>(provisionService.updateProvision(id, provisionDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}/quantity")
    public ProvisionDto updateProvisionQuantity(@PathVariable int id, @RequestParam int units) {
        return provisionService.updateProvisionQuantity(id, units);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvisionById(@PathVariable int id) {
        provisionService.deleteProvisionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}