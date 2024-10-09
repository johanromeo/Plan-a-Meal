package com.jromeo.backend.shoppinglist.service;

import com.jromeo.backend.provision.dto.ProvisionDTO;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import com.jromeo.backend.shoppinglist.dto.ShoppingListDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;

    public ShoppingListService(ProvisionRepository provisionRepository, ProvisionMapper provisionMapper) {
        this.provisionRepository = provisionRepository;
        this.provisionMapper = provisionMapper;
    }

    public ShoppingListDTO addProvisionsToShoppingList() {
        List<ProvisionEntity> provisionEntities = provisionRepository.addProvisionToShoppingList();
        List<ProvisionDTO> provisionDTOs = new ArrayList<>();

        for (ProvisionEntity provisionEntity : provisionEntities) {
            provisionDTOs.add(provisionMapper.mapToDTO(provisionEntity));
        }

        ShoppingListDTO provisionsToGroceryShoppingList = new ShoppingListDTO();
        provisionsToGroceryShoppingList.setProvisionsToShoppingList(provisionDTOs);

        return provisionsToGroceryShoppingList;
    }
}