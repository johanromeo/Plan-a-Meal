package com.jromeo.backend.provision.service;

import com.jromeo.backend.exceptions.ProvisionNotFoundException;
import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for performing CRUD operations on {@link ProvisionDto} object(s).
 *
 * @author Johan Romeo
 */
@Service
@RequiredArgsConstructor
public class ProvisionService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;

    public void addProvision(ProvisionDto provisionDTO) {
        ProvisionEntity provisionEntity = provisionMapper.mapToEntity(provisionDTO);

        provisionRepository.save(provisionEntity);
    }

    public ProvisionDto findProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id " + id + " exist"));

        return provisionMapper.mapToDto(provisionEntity);
    }

    public List<ProvisionDto> findAllProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAll();

        return provisionMapper.mapToDtos(provisionEntities);
    }

    public List<ProvisionDto> findAllProvisionsBySortedDescName() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAllBySortedDescName();

        return provisionMapper.mapToDtos(provisionEntities);
    }

    public List<ProvisionDto> findAllPositiveProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAllAvailableProvisionsInHousehold();

        return provisionMapper.mapToDtos(provisionEntities);
    }

    public ProvisionDto updateProvision(int id, ProvisionDto provisionDTO) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id " + id + " exists"));
        provisionEntity.setName(provisionDTO.getName());
        provisionEntity.setUnits(provisionDTO.getUnits());
        provisionEntity.setAddedToGroceryShoppingList(provisionDTO.isAddedToGroceryShoppingList());
        provisionEntity.setImgUrl(provisionDTO.getImgUrl());

        provisionRepository.save(provisionEntity);

        return provisionMapper.mapToDto(provisionEntity);
    }

    @Transactional
    public ProvisionDto updateProvisionQuantity(int id, int units) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id " + id + " exists"));
        provisionEntity.setUnits(units);

        provisionRepository.save(provisionEntity);

        return provisionMapper.mapToDto(provisionEntity);
    }

    public void deleteProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id" + id + " exists"));

        provisionRepository.delete(provisionEntity);
    }
}
