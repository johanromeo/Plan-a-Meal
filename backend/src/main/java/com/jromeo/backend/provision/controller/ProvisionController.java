package com.jromeo.backend.provision.controller;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.service.ProvisionService;
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
    public void addProvision(@RequestBody ProvisionDto provisionDTO) {
        provisionService.addProvision(provisionDTO);
    }

    @GetMapping("/{id}")
    public ProvisionDto findProvisionById(@PathVariable int id) {
        return provisionService.findProvisionById(id);
    }

    @GetMapping("/{name}")
    public ProvisionDto findProvisionByName(@PathVariable String name) {
        return provisionService.findProvisionByName(name);
    }

    @GetMapping
    public List<ProvisionDto> findAllProvisions() {
        return provisionService.findAllProvisions();
    }

    @PutMapping("/{id}")
    public ProvisionDto updateProvision(@PathVariable int id, @RequestBody ProvisionDto provisionDTO) {
        return provisionService.updateProvision(id, provisionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProvisionById(@PathVariable int id) {
        provisionService.deleteProvisionById(id);
    }
}