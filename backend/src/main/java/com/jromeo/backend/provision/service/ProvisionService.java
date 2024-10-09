package com.jromeo.backend.provision.service;

import com.jromeo.backend.provision.dto.ProvisionDTO;
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

    public void addProvision(ProvisionDTO provisionDTO) {
        ProvisionEntity provisionEntity = provisionMapper.mapToEntity(provisionDTO);

        provisionRepository.save(provisionEntity);
    }

    public ProvisionDTO findProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No provision with id " + id + " exists"));

        return provisionMapper.mapToDTO(provisionEntity);
    }

    public ProvisionDTO findProvisionByName(String name) {
        ProvisionEntity provisionEntity = provisionRepository.findByName(name);

        return provisionMapper.mapToDTO(provisionEntity);
    }

    public List<ProvisionDTO> findAllProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAll();

        return provisionMapper.mapToDTOs(provisionEntities);
    }

    public ProvisionDTO updateProvision(int id, ProvisionDTO provisionDTO) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No provision with id " + id + " exists"));
        provisionEntity.setName(provisionDTO.getName());
        provisionEntity.setUnits(provisionDTO.getUnits());
        provisionEntity.setAddedToGroceryShoppingList(provisionDTO.isAddedToShoppingList());

        provisionRepository.save(provisionEntity);

        return provisionMapper.mapToDTO(provisionEntity);
    }

    public void deleteProvisionById(int id) {
        ProvisionEntity provisionEntity = provisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No provision with id" + id + " exists"));

        provisionRepository.delete(provisionEntity);
    }
}
