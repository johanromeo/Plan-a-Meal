package com.jromeo.backend.provision.service;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation class for calling the repository layer {@link ProvisionRepository}.
 * Class is using {@link ProvisionMapper} to map between Entities and DTOs.
 *
 * @author Johan Romeo
 */
@Service
public class ProvisionService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;

    public ProvisionService(ProvisionRepository provisionRepository, ProvisionMapper provisionMapper) {
        this.provisionRepository = provisionRepository;
        this.provisionMapper = provisionMapper;
    }

    /**
     * Maps a Provision DTO to a Provision Entity and stores it MySQL database.
     * @param provisionDTO the {@link ProvisionDto} to be stored.
     */
    public void addProvision(ProvisionDto provisionDTO) {
        ProvisionEntity provisionEntity = provisionMapper.mapToEntity(provisionDTO);

        provisionRepository.save(provisionEntity);
    }

    /**
     * Finds a provision by its id from MySQL database.
     * @param id the id of the provision.
     * @return a mapped {@link ProvisionDto} object.
     */
    public ProvisionDto findProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No provision with id " + id + " exists"));

        return provisionMapper.mapToDto(provisionEntity);
    }

    /**
     * Finds a provision by its name from MySQL database.
     * @param name the name of the provision.
     * @return a mapped {@link ProvisionDto} object.
     */
    public ProvisionDto findProvisionByName(String name) {
        ProvisionEntity provisionEntity = provisionRepository.findByName(name);

        return provisionMapper.mapToDto(provisionEntity);
    }

    /**
     * Finds all the provisions stored in MySQL database.
     * @return a List of mapped {@link ProvisionDto} objects.
     */
    public List<ProvisionDto> findAllProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAll();

        return provisionMapper.mapToDtos(provisionEntities);
    }

    /**
     * Finds all provisions in MySQL database that are greater than 0 in the "number_of_units" field.
     * @return a List of mapped {@link ProvisionDto} objects.
     */
    public List<ProvisionDto> findAllPositiveProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAllPositiveProvisions();

        return provisionMapper.mapToDtos(provisionEntities);
    }

    /**
     * Updates the fields of a provision in MySQL database.
     * @param id the id of the provision to be updated.
     * @param provisionDTO the request body by the user, specifying which fields to be updated.
     * @return a mapped {@link ProvisionDto}.
     */
    public ProvisionDto updateProvision(int id, ProvisionDto provisionDTO) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No provision with id " + id + " exists"));
        provisionEntity.setName(provisionDTO.getName());
        provisionEntity.setUnits(provisionDTO.getUnits());
        provisionEntity.setAddedToGroceryShoppingList(provisionDTO.isAddedToShoppingList());

        provisionRepository.save(provisionEntity);

        return provisionMapper.mapToDto(provisionEntity);
    }

    /**
     * Delete a provision by its id from MySQL database.
     * @param id the id of the provision to be deleted.
     */
    public void deleteProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No provision with id" + id + " exists"));

        provisionRepository.delete(provisionEntity);
    }
}
