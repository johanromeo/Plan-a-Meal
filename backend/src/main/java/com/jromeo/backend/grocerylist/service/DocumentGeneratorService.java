package com.jromeo.backend.grocerylist.service;

import com.jromeo.backend.provision.dto.ProvisionDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentGeneratorService {

    public String provisionsToBuy(List<ProvisionDto> provisionDtos) {
        StringBuilder sb = new StringBuilder();
        for (ProvisionDto provisionDto : provisionDtos) {
            sb.append(provisionDto.getName()).append("\n");
        }
        return sb.toString();
    }
}
