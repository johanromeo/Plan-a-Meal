package com.jromeo.backend.provision.mapper;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProvisionMapper {

    public ProvisionDto mapToDto(ProvisionEntity provisionEntity) {
        ProvisionDto provisionDto = new ProvisionDto(
                provisionEntity.getId(),
                provisionEntity.getName(),
                provisionEntity.getUnits(),
                provisionEntity.isAddedToGroceryShoppingList()
        );

        return provisionDto;
    }

    public List<ProvisionDto> mapToDtos(List<ProvisionEntity> provisionEntities) {
        List<ProvisionDto> provisionDtos = new ArrayList<>();
        for (ProvisionEntity provisionEntity : provisionEntities) {
            provisionDtos.add(mapToDto(provisionEntity));
        }

        return provisionDtos;
    }

    public ProvisionEntity mapToEntity(ProvisionDto provisionDTO) {
        ProvisionEntity provisionEntity = new ProvisionEntity(
                provisionDTO.getId(),
                provisionDTO.getName(),
                provisionDTO.getUnits(),
                provisionDTO.isAddedToShoppingList()
        );

        return provisionEntity;
    }
}
