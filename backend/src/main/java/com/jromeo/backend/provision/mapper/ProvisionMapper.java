package com.jromeo.backend.provision.mapper;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Provision mapper.
 *
 * @author Johan Romeo
 */
@Component
public class ProvisionMapper {

    /**
     * Map to dto provision dto.
     *
     * @param provisionEntity the provision entity
     * @return the provision dto
     * @author Johan Romeo
     */
    public ProvisionDto mapToDto(ProvisionEntity provisionEntity) {
        ProvisionDto provisionDto = new ProvisionDto(
                provisionEntity.getId(),
                provisionEntity.getName(),
                provisionEntity.getUnits(),
                provisionEntity.isAddedToGroceryShoppingList()
        );

        return provisionDto;
    }

    /**
     * Map to dtos list.
     *
     * @param provisionEntities the provision entities
     * @return the list
     * @author Johan Romeo
     */
    public List<ProvisionDto> mapToDtos(List<ProvisionEntity> provisionEntities) {
        List<ProvisionDto> provisionDtos = new ArrayList<>();
        for (ProvisionEntity provisionEntity : provisionEntities) {
            provisionDtos.add(mapToDto(provisionEntity));
        }

        return provisionDtos;
    }

    /**
     * Map to entity provision entity.
     *
     * @param provisionDTO the provision dto
     * @return the provision entity
     * @author Johan Romeo
     */
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
