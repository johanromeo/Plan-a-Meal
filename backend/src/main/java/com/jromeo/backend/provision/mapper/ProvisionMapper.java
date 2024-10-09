package com.jromeo.backend.provision.mapper;

import com.jromeo.backend.provision.dto.ProvisionDTO;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProvisionMapper {

    public ProvisionDTO mapToDTO(ProvisionEntity provisionEntity) {
        ProvisionDTO  provisionDTO = new ProvisionDTO(
                provisionEntity.getId(),
                provisionEntity.getName(),
                provisionEntity.getUnits(),
                provisionEntity.isAddedToGroceryShoppingList()
        );

        return provisionDTO;
    }

    public List<ProvisionDTO> mapToDTOs(List<ProvisionEntity> provisionEntities) {
        List<ProvisionDTO> provisionDTOs = new ArrayList<>();
        for (ProvisionEntity provisionEntity : provisionEntities) {
            provisionDTOs.add(mapToDTO(provisionEntity));
        }

        return provisionDTOs;
    }

    public ProvisionEntity mapToEntity(ProvisionDTO provisionDTO) {
        ProvisionEntity provisionEntity = new ProvisionEntity(
                provisionDTO.getId(),
                provisionDTO.getName(),
                provisionDTO.getUnits(),
                provisionDTO.isAddedToShoppingList()
        );

        return provisionEntity;
    }

    public List<ProvisionEntity> mapToEntities(List<ProvisionDTO> provisionDTOs) {
        List<ProvisionEntity> provisionEntities = new ArrayList<>();
        for (ProvisionDTO provisionDTO : provisionDTOs) {
            provisionEntities.add(mapToEntity(provisionDTO));
        }

        return provisionEntities;
    }
}
