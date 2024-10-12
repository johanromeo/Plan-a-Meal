package com.jromeo.backend.shoppinglist.service;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import com.jromeo.backend.shoppinglist.dto.ShoppingListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;

    public ShoppingListService(ProvisionRepository provisionRepository, ProvisionMapper provisionMapper) {
        this.provisionRepository = provisionRepository;
        this.provisionMapper = provisionMapper;
    }

    public ShoppingListDto addProvisionsToShoppingList() {
        List<ProvisionEntity> provisionEntities = provisionRepository.addProvisionToShoppingList();

        ShoppingListDto provisionsToShoppingList = new ShoppingListDto();

        provisionsToShoppingList.setProvisionsToShoppingList(provisionMapper.mapToDtos(provisionEntities));

        return provisionsToShoppingList;
    }
}
