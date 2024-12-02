package com.jromeo.backend.grocerylist.service;

import com.jromeo.backend.person.service.PersonService;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for constructing an email with provisions.
 *
 * @author Johan Romeo
 */
@Service
@RequiredArgsConstructor
public class GroceryShoppingListService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;
    private final EmailDeliveryService emailDeliveryService;
    private final DocumentGeneratorService documentGeneratorService;
    private final PersonService personService;

    /**
     * Sends an email to the user(s) with what provisions to buy.
     *
     */
    public void sendEmailWithProvisions() {
        List<ProvisionEntity> provisionEntities = provisionRepository.findAllProvisionsToBeAddedToGroceryShoppingList();

        String provisionsToBuy = documentGeneratorService.provisionsToBuy(provisionMapper.mapToDtos(provisionEntities));

        String[] emailAddresses = personService.getPeopleEmailAddresses();

        emailDeliveryService.sendGroceryShoppingListToMail(provisionsToBuy, emailAddresses);
    }
}
