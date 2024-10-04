package com.jromeo.backend.provision.mapper;

import com.jromeo.backend.provision.dto.ProvisionDTO;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import org.springframework.stereotype.Component;

@Component
public class ProvisionMapper {

    public ProvisionDTO mapToDTO(ProvisionEntity provisionEntity) {
        ProvisionDTO  provisionDTO = new ProvisionDTO(
                provisionEntity.getName(),
                provisionEntity.getUnits(),
                provisionEntity.isAddedToGroceryShoppingList()
        );

        return provisionDTO;
    }

    public ProvisionEntity mapToEntity(ProvisionDTO provisionDTO) {
        ProvisionEntity provisionEntity = new ProvisionEntity(
                provisionDTO.getName(),
                provisionDTO.getUnits(),
                provisionDTO.isAddedToGroceryShoppingList()
        );

        return provisionEntity;
    }
}
