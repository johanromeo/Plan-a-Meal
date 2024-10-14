package com.jromeo.backend.provision.controller;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for exposing API-endpoints for manipulating the "provisions" table in a MySQL database.
 * Calls the service layer {@link ProvisionService}.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/provisions")
public class ProvisionController {

    private final ProvisionService provisionService;

    public ProvisionController(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }

    /**
     * Stores a provision to MySQL database.
     * @param provisionDTO the provision to be added by the user
     */
    @PostMapping
    public void addProvision(@RequestBody ProvisionDto provisionDTO) {
        provisionService.addProvision(provisionDTO);
    }

    /**
     * Finds a provision by its id from MySQL database.
     * @param id the id of the provision.
     * @return a {@link ProvisionDto} object.
     */
    @GetMapping("/{id}")
    public ProvisionDto findProvisionById(@PathVariable int id) {
        return provisionService.findProvisionById(id);
    }

    /**
     * Finds a provision by its name from MySQL database.
     * @param name the name of the provision.
     * @return a {@link ProvisionDto} object.
     */
    @GetMapping("/{name}")
    public ProvisionDto findProvisionByName(@PathVariable String name) {
        return provisionService.findProvisionByName(name);
    }

    /**
     * Shows all the provisions stored in MySQL database.
     * @return a List of {@link ProvisionDto} objects.
     */
    @GetMapping
    public List<ProvisionDto> findAllProvisions() {
        return provisionService.findAllProvisions();
    }

    /**
     * Updates the fields of a provision in MySQL database.
     * @param id the id of the provision to be updated.
     * @param provisionDTO the request body by the user, specifying which fields to be updated.
     * @return
     */
    @PutMapping("/{id}")
    public ProvisionDto updateProvision(@PathVariable int id, @RequestBody ProvisionDto provisionDTO) {
        return provisionService.updateProvision(id, provisionDTO);
    }

    /**
     * Delete a provision by its id from MySQL database.
     * @param id the id of the provision to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteProvisionById(@PathVariable int id) {
        provisionService.deleteProvisionById(id);
    }
}