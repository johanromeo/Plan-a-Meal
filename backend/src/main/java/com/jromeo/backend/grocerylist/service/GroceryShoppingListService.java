package com.jromeo.backend.grocerylist.service;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import com.jromeo.backend.grocerylist.dto.GroceryShoppingListDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling operations related to the grocery shopping list.
 * This class interacts with the {@link ProvisionRepository} to retrieve provisions that are to be
 * added to the grocery shopping list and maps them to DTOs using the {@link ProvisionMapper}.
 *
 * @author Johan Romeo
 */
@Service
public class GroceryShoppingListService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;

    public GroceryShoppingListService(ProvisionRepository provisionRepository, ProvisionMapper provisionMapper) {
        this.provisionRepository = provisionRepository;
        this.provisionMapper = provisionMapper;
    }

    public GroceryShoppingListDto addProvisionsToShoppingList() {
        List<ProvisionEntity> provisionEntities = provisionRepository.addProvisionToShoppingList();

        GroceryShoppingListDto provisionsToShoppingList = new GroceryShoppingListDto();

        provisionsToShoppingList.setProvisionsToShoppingList(provisionMapper.mapToDtos(provisionEntities));

        return provisionsToShoppingList;
    }
}
