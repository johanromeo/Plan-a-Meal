package com.jromeo.backend.provision.service;

import com.jromeo.backend.provision.dto.ProvisionDTO;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvisionService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;

    public ProvisionService(ProvisionRepository provisionRepository, ProvisionMapper provisionMapper) {
        this.provisionRepository = provisionRepository;
        this.provisionMapper = provisionMapper;
    }

    public void addProvision(ProvisionDTO provisionDTO) {
        ProvisionEntity provisionEntity = provisionMapper.mapToEntity(provisionDTO);
        provisionRepository.save(provisionEntity);
    }

    public ProvisionDTO findProvisionByName(String name) {
        ProvisionEntity provisionEntity = provisionRepository.findByName(name);
        return provisionMapper.mapToDTO(provisionEntity);
    }

    public List<ProvisionDTO> findAllProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAll();
        List<ProvisionDTO> provisionDTOs = new ArrayList<>();

        for(ProvisionEntity provision : provisionEntities) {
            provisionDTOs.add(provisionMapper.mapToDTO(provision));
        }

        return provisionDTOs;
    }

    public ProvisionDTO updateProvision(int id, ProvisionDTO provisionDTO) {
        ProvisionEntity updatedProvisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No provision with id" + id + " exists"));
        updatedProvisionEntity.setName(provisionDTO.getName());
        updatedProvisionEntity.setUnits(provisionDTO.getUnits());
        updatedProvisionEntity.setAddedToGroceryShoppingList(provisionDTO.isAddedToGroceryShoppingList());

        provisionRepository.save(updatedProvisionEntity);
        return provisionMapper.mapToDTO(updatedProvisionEntity);
    }
}
