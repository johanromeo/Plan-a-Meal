package com.jromeo.backend.provision.mapper;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.service.ProvisionService;
import com.jromeo.backend.grocerylist.service.GroceryShoppingListService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for mapping provisions from DTO to Entity and Entity to DTO.
 * Used by {@link ProvisionService} and {@link GroceryShoppingListService}.
 *
 * @author Johan Romeo
 */
@Component
public class ProvisionMapper {

    /**
     * Maps a {@link ProvisionEntity} object to a {@link ProvisionDto} object.
     * @param provisionEntity the {@link ProvisionEntity} object to be mapped.
     * @return the mapped {@link ProvisionDto} object.
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
     * Maps a List of {@link ProvisionEntity} objects to a List of {@link ProvisionDto} objects.
     * @param provisionEntities the {@link ProvisionEntity} objects to be mapped.
     * @return the mapped {@link ProvisionDto} objects.
     */
    public List<ProvisionDto> mapToDtos(List<ProvisionEntity> provisionEntities) {
        List<ProvisionDto> provisionDtos = new ArrayList<>();
        for (ProvisionEntity provisionEntity : provisionEntities) {
            provisionDtos.add(mapToDto(provisionEntity));
        }

        return provisionDtos;
    }

    /**
     * Maps a {@link ProvisionDto} object to a {@link ProvisionEntity} object.
     * @param provisionDTO the {@link ProvisionDto} to be mapped.
     * @return a {@link ProvisionEntity}
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
