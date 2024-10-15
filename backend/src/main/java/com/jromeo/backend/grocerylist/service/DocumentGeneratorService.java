package com.jromeo.backend.grocerylist.service;

import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import com.jromeo.backend.grocerylist.dto.GroceryShoppingListDto;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Service class responsible for generating a shopping list document.
 * This class interacts with the {@link GroceryShoppingListService} to retrieve a list of provisions
 * and generates a text file that represents the shopping list.
 *
 * @author Johan Romeo
 */
@Service
public class DocumentGeneratorService {


    private final GroceryShoppingListService groceryShoppingListService;
    private final static String SHOPPING_LIST_BEGINNING = "GROCERY SHOPPING LIST";
    private final static String SHOPPING_LIST_LINE_BREAK = "______________________";


    public DocumentGeneratorService(GroceryShoppingListService groceryShoppingListService) {
        this.groceryShoppingListService = groceryShoppingListService;
    }

    /**
     * Retrieves provisions from the database that are to be added to the shopping list.
     * The provisions are fetched from the {@link ProvisionRepository}, mapped to DTOs using
     * {@link ProvisionMapper}, and returned as part of a {@link GroceryShoppingListDto}.
     *
     * @return a {@link GroceryShoppingListDto} containing the list of provisions to be included in the shopping list.
     */
    public void createShoppingListDocx() throws IOException {
        File file = new File("shopping-list.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        GroceryShoppingListDto groceryShoppingListDTO = groceryShoppingListService.addProvisionsToShoppingList();
        List<ProvisionDto> provisions = groceryShoppingListDTO.getProvisionsToShoppingList();

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
