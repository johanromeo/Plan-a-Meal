package com.jromeo.backend.grocerylist.service;

import com.jromeo.backend.person.service.PersonService;
import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryShoppingListService {

    private final ProvisionRepository provisionRepository;
    private final ProvisionMapper provisionMapper;
    private final EmailDeliveryService emailDeliveryService;
    private final DocumentGeneratorService documentGeneratorService;
    private final PersonService personService;

    public GroceryShoppingListService(
            ProvisionRepository provisionRepository, ProvisionMapper provisionMapper,
            EmailDeliveryService emailDeliveryService,
            DocumentGeneratorService documentGeneratorService, PersonService personService) {

        this.provisionRepository = provisionRepository;
        this.provisionMapper = provisionMapper;
        this.emailDeliveryService = emailDeliveryService;
        this.documentGeneratorService = documentGeneratorService;
        this.personService = personService;
    }

    public void constructEmailWithProvisions() throws MessagingException{
        List<ProvisionEntity> provisionEntities = provisionRepository.addProvisionToShoppingList();

        String provisionsToBuy = documentGeneratorService.provisionsToBuy(provisionMapper.mapToDtos(provisionEntities));

        String[] emailAddresses = personService.getPeopleEmailAddresses();

        emailDeliveryService.sendGroceryShoppingListToMail(provisionsToBuy, emailAddresses);
    }
}
