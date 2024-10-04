package com.jromeo.backend.provision.controller;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provision")
public class ProvisionController {

    private final ProvisionService provisionService;

    public ProvisionController(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }

    @PostMapping
    public void addProvision(@RequestBody ProvisionEntity provision) {
        provisionService.addGrocery(provision);
    }
}