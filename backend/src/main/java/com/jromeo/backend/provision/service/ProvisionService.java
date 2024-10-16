package com.jromeo.backend.provision.service;

import com.jromeo.backend.exceptions.ProvisionNotFoundException;
import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvisionService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;

    public ProvisionService(ProvisionRepository provisionRepository, ProvisionMapper provisionMapper) {
        this.provisionRepository = provisionRepository;
        this.provisionMapper = provisionMapper;
    }

    public void addProvision(ProvisionDto provisionDTO) {
        ProvisionEntity provisionEntity = provisionMapper.mapToEntity(provisionDTO);

        provisionRepository.save(provisionEntity);
    }

    public ProvisionDto findProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id " + id + " exists"));

        return provisionMapper.mapToDto(provisionEntity);
    }

    public List<ProvisionDto> findAllProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAll();

        return provisionMapper.mapToDtos(provisionEntities);
    }

    public List<ProvisionDto> findAllPositiveProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAllPositiveProvisions();

        return provisionMapper.mapToDtos(provisionEntities);
    }

    public ProvisionDto updateProvision(int id, ProvisionDto provisionDTO) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id " + id + " exists"));
        provisionEntity.setName(provisionDTO.getName());
        provisionEntity.setUnits(provisionDTO.getUnits());
        provisionEntity.setAddedToGroceryShoppingList(provisionDTO.isAddedToShoppingList());

        provisionRepository.save(provisionEntity);

        return provisionMapper.mapToDto(provisionEntity);
    }

    public void deleteProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new ProvisionNotFoundException("No provision with id" + id + " exists"));

        provisionRepository.delete(provisionEntity);
    }
}
