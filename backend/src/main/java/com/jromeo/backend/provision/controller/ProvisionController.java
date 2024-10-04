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

    @GetMapping("/{name}")
    public ProvisionDTO findProvisionByName(@PathVariable String name) {
        return provisionService.findProvisionByName(name);
    }

    @GetMapping
    public List<ProvisionDTO> findAllProvisions() {
        return provisionService.findAllProvisions();
    }
}