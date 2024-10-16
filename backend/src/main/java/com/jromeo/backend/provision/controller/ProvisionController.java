package com.jromeo.backend.provision.controller;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provisions")
public class ProvisionController {

    private final ProvisionService provisionService;

    public ProvisionController(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvisionById(@PathVariable int id) {
        provisionService.deleteProvisionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}