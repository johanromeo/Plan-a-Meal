package com.jromeo.backend.provision.controller;

import com.jromeo.backend.provision.dto.ProvisionDTO;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provision")
public class ProvisionController {

    private final ProvisionService provisionService;

    public ProvisionController(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }

    @PostMapping
    public void addProvision(@RequestBody ProvisionDTO provisionDTO) {
        provisionService.addProvision(provisionDTO);
    }

    @GetMapping("/{id}")
    public ProvisionDTO findProvisionById(@PathVariable int id) {
        return provisionService.findProvisionById(id);
    }

    @GetMapping("/{name}")
    public ProvisionDTO findProvisionByName(@PathVariable String name) {
        return provisionService.findProvisionByName(name);
    }

    @GetMapping
    public List<ProvisionDTO> findAllProvisions() {
        return provisionService.findAllProvisions();
    }

    @PutMapping("/{id}")
    public ProvisionDTO updateProvision(@PathVariable int id, @RequestBody ProvisionDTO provisionDTO) {
        return provisionService.updateProvision(id, provisionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProvisionById(@PathVariable int id) {
        provisionService.deleteProvisionById(id);
    }
}