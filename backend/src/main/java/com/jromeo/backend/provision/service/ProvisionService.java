package com.jromeo.backend.provision.service;

import com.jromeo.backend.exceptions.ProvisionNotFoundException;
import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Provision service.
 *
 * @author Johan Romeo
 */
@Service
public class ProvisionService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;

    /**
     * Instantiates a new Provision service.
     *
     * @param provisionRepository the provision repository
     * @param provisionMapper     the provision mapper
     * @author Johan Romeo
     */
    public ProvisionService(ProvisionRepository provisionRepository, ProvisionMapper provisionMapper) {
        this.provisionRepository = provisionRepository;
        this.provisionMapper = provisionMapper;
    }

    /**
     * Add provision.
     *
     * @param provisionDTO the provision dto
     * @author Johan Romeo
     */
    public void addProvision(ProvisionDto provisionDTO) {
        ProvisionEntity provisionEntity = provisionMapper.mapToEntity(provisionDTO);

        provisionRepository.save(provisionEntity);
    }

    /**
     * Find provision by id provision dto.
     *
     * @param id the id
     * @return the provision dto
     * @author Johan Romeo
     */
    public ProvisionDto findProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id " + id + " exist"));

        return provisionMapper.mapToDto(provisionEntity);
    }

    /**
     * Find all provisions list.
     *
     * @return the list
     * @author Johan Romeo
     */
    public List<ProvisionDto> findAllProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAll();

        return provisionMapper.mapToDtos(provisionEntities);
    }

    /**
     * Find all positive provisions list.
     *
     * @return the list
     * @author Johan Romeo
     */
    public List<ProvisionDto> findAllPositiveProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAllPositiveProvisions();

        return provisionMapper.mapToDtos(provisionEntities);
    }

    /**
     * Update provision provision dto.
     *
     * @param id           the id
     * @param provisionDTO the provision dto
     * @return the provision dto
     * @author Johan Romeo
     */
    public ProvisionDto updateProvision(int id, ProvisionDto provisionDTO) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id " + id + " exists"));
        provisionEntity.setName(provisionDTO.getName());
        provisionEntity.setUnits(provisionDTO.getUnits());
        provisionEntity.setAddedToGroceryShoppingList(provisionDTO.isAddedToShoppingList());
        provisionEntity.setImgUrl(provisionDTO.getImgUrl());

        provisionRepository.save(provisionEntity);

        return provisionMapper.mapToDto(provisionEntity);
    }

    /**
     * Update provision quantity.
     *
     * @param id     the id of the provision
     * @param units  the new quantity for the provision
     * @return the updated provision dto
     */
    @Transactional
    public ProvisionDto updateProvisionQuantity(int id, int units) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id " + id + " exists"));
        provisionEntity.setUnits(units);

        provisionRepository.save(provisionEntity);

        return provisionMapper.mapToDto(provisionEntity);
    }

    /**
     * Delete provision by id.
     *
     * @param id the id
     * @author Johan Romeo
     */
    public void deleteProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id" + id + " exists"));

        provisionRepository.delete(provisionEntity);
    }
}
