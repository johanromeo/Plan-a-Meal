package com.jromeo.backend.grocerylist.service;

import com.jromeo.backend.provision.dto.ProvisionDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type Document generator service.
 *
 * @author Johan Romeo
 */
@Component
public class DocumentGeneratorService {

    /**
     * Provisions to buy string.
     *
     * @param provisionDtos the provision dtos
     * @return the string
     * @author Johan Romeo
     */
    public String provisionsToBuy(List<ProvisionDto> provisionDtos) {
        StringBuilder sb = new StringBuilder();
        for (ProvisionDto provisionDto : provisionDtos) {
            sb.append(provisionDto.getName()).append("\n");
        }
        return sb.toString();
    }
}
