package com.jromeo.backend.provision.controller;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Provision controller.
 *
 * @author Johan Romeo
 */
@RestController
@RequestMapping("/provisions")
public class ProvisionController {

    private final ProvisionService provisionService;

    /**
     * Instantiates a new Provision controller.
     *
     * @param provisionService the provision service
     * @author Johan Romeo
     */
    public ProvisionController(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }

    /**
     * Add provision response entity.
     *
     * @param provisionDTO the provision dto
     * @return the response entity
     * @author Johan Romeo
     */
    @PostMapping
    public ResponseEntity<String> addProvision(@RequestBody ProvisionDto provisionDTO) {
        provisionService.addProvision(provisionDTO);
        return new ResponseEntity<>("Provision added", HttpStatus.CREATED);
    }

    /**
     * Find provision by id response entity.
     *
     * @param id the id
     * @return the response entity
     * @author Johan Romeo
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProvisionDto> findProvisionById(@PathVariable int id) {
        return new ResponseEntity<>(provisionService.findProvisionById(id), HttpStatus.OK);
    }

    /**
     * Find all provisions response entity.
     *
     * @return the response entity
     * @author Johan Romeo
     */
    @GetMapping
    public ResponseEntity<List<ProvisionDto>> findAllProvisions() {
        return new ResponseEntity<>(provisionService.findAllProvisions(), HttpStatus.OK);
    }

    /**
     * Update provision response entity.
     *
     * @param id           the id
     * @param provisionDTO the provision dto
     * @return the response entity
     * @author Johan Romeo
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProvisionDto> updateProvision(@PathVariable int id, @RequestBody ProvisionDto provisionDTO) {
        return new ResponseEntity<>(provisionService.updateProvision(id, provisionDTO), HttpStatus.OK);
    }

    /**
     * Delete provision by id response entity.
     *
     * @param id the id
     * @return the response entity
     * @author Johan Romeo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvisionById(@PathVariable int id) {
        provisionService.deleteProvisionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}