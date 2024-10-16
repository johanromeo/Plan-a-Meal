package com.jromeo.backend.grocerylist.service;

import com.jromeo.backend.grocerylist.dto.GroceryShoppingListDto;
import com.jromeo.backend.provision.dto.ProvisionDto;
import com.jromeo.backend.provision.mapper.ProvisionMapper;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Service class responsible for generating a grocery shopping list document.
 * This class interacts with the {@link GroceryShoppingListService} to retrieve a list of provisions
 * and generates a text file that represents the grocery shopping list.
 *
 * @author Johan Romeo
 */
@Service
public class DocumentGeneratorService {

    private final static String SHOPPING_LIST_BEGINNING = "GROCERY SHOPPING LIST";
    private final static String SHOPPING_LIST_LINE_BREAK = "______________________";


    /**
     * Retrieves provisions from the database that are to be added to the grocery shopping list.
     * The provisions are fetched from the {@link ProvisionRepository}, mapped to DTOs using
     * {@link ProvisionMapper}, and returned as part of a {@link GroceryShoppingListDto}.
     *
     * @return a {@link GroceryShoppingListDto} containing the list of provisions to be included in the grocery shopping list.
     */
    public void createShoppingListDocx(List<ProvisionDto> provisions) throws IOException {

        File file = new File("grocery-shopping-list.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

//        GroceryShoppingListDto groceryShoppingListDTO = new GroceryShoppingListDto(provisions);
//        List<ProvisionDto> provisionss = groceryShoppingListDTO.getProvisionsToShoppingList();

        writer.write(SHOPPING_LIST_BEGINNING);
        writer.newLine();
        writer.write(SHOPPING_LIST_LINE_BREAK);
        writer.newLine();
        for (ProvisionDto provision: provisions) {
            writer.write(provision.getName());
            writer.newLine();
        }
        writer.write(SHOPPING_LIST_LINE_BREAK);
        writer.close();

    }

    public String provisionsToBuy(List<ProvisionDto> provisionDtos) {
        StringBuilder sb = new StringBuilder();
        for (ProvisionDto provisionDto : provisionDtos) {
            sb.append(provisionDto.getName()).append("\n");
        }
        return sb.toString();
    }
}
