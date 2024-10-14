package com.jromeo.backend.shoppinglist.service;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.shoppinglist.dto.ShoppingListDto;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Service class responsible for generating a shopping list document.
 * This class interacts with the {@link ShoppingListService} to retrieve a list of provisions
 * and generates a text file that represents the shopping list.
 *
 * @author Johan Romeo
 */
@Service
public class DocumentGeneratorService {


    private final ShoppingListService shoppingListService;
    private final static String SHOPPING_LIST_BEGINNING = "GROCERY SHOPPING LIST";
    private final static String SHOPPING_LIST_LINE_BREAK = "______________________";


    public DocumentGeneratorService(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    public void createShoppingListDocx() throws IOException {
        File file = new File("shopping-list.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        ShoppingListDto shoppingListDTO = shoppingListService.addProvisionsToShoppingList();
        List<ProvisionDto> provisions = shoppingListDTO.getProvisionsToShoppingList();

        writer.write(SHOPPING_LIST_BEGINNING);
        writer.newLine();
        writer.write(SHOPPING_LIST_LINE_BREAK);
        writer.newLine();
        for (ProvisionDto provision : provisions) {
            writer.write(provision.getName());
            writer.newLine();
        }
        writer.write(SHOPPING_LIST_LINE_BREAK);
        writer.close();
    }
}
