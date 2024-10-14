package com.jromeo.backend.shoppinglist.service;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import com.jromeo.backend.shoppinglist.dto.ShoppingListDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling operations related to the shopping list.
 * This class interacts with the {@link ProvisionRepository} to retrieve provisions that are to be
 * added to the shopping list and maps them to DTOs using the {@link ProvisionMapper}.
 *
 * @author Johan Romeo
 */
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
