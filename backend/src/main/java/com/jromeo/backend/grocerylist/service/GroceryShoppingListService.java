package com.jromeo.backend.grocerylist.service;

import com.jromeo.backend.person.PersonService;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private final EmailDeliveryService emailDeliveryService;
    private final DocumentGeneratorService documentGeneratorService;
    private final PersonService personService;

    public GroceryShoppingListService(ProvisionRepository provisionRepository, ProvisionMapper provisionMapper, EmailDeliveryService emailDeliveryService, DocumentGeneratorService documentGeneratorService, PersonService personService) {
        this.provisionRepository = provisionRepository;
        this.provisionMapper = provisionMapper;
        this.emailDeliveryService = emailDeliveryService;
        this.documentGeneratorService = documentGeneratorService;
        this.personService = personService;
    }

    public void constructEmailWithProvisions() throws IOException, MessagingException, InterruptedException {
        List<ProvisionEntity> provisionEntities = provisionRepository.addProvisionToShoppingList();

        String provisionsToBuy = documentGeneratorService.provisionsToBuy(provisionMapper.mapToDtos(provisionEntities));
        String[] emailAddresses = personService.getHouseholdEmailAddresses();
        emailDeliveryService.sendGroceryShoppingListToMail(provisionsToBuy, emailAddresses);


    }
}
