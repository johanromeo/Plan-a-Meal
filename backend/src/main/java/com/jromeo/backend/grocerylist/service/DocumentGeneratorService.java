package com.jromeo.backend.grocerylist.service;

import com.jromeo.backend.provision.dto.ProvisionDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Helper class for returning what provisions to be put in the grocery shopping list.
 *
 * @author Johan Romeo
 */
@Component
public class DocumentGeneratorService {

    /**
     * Append each Provision name to a String Builder object.
     *
     * @param provisionDtos the provisions to append.
     * @return a toString() of the String Builder object containing the provisions.
     */
    public String provisionsToBuy(List<ProvisionDto> provisionDtos) {
        StringBuilder sb = new StringBuilder();
        for (ProvisionDto provisionDto : provisionDtos) {
            sb.append(provisionDto.getName()).append("\n");
        }
        return sb.toString();
    }
}
