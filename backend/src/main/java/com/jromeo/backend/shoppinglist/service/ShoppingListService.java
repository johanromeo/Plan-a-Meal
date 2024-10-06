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

    public ShoppingListDTO addProvisionsToGroceryShoppingList() {
        List<ProvisionEntity> provisionsToBeAdded = provisionRepository.addProvisionToShoppingList();
        List<ProvisionDTO> dtos = new ArrayList<>();

        for (ProvisionEntity provisionEntity : provisionsToBeAdded) {
            dtos.add(provisionMapper.mapToDTO(provisionEntity));
        }

        ShoppingListDTO list = new ShoppingListDTO();
        list.setProvisionsToShoppingList(dtos);


        return list;
    }
}
