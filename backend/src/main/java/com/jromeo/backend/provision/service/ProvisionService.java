package com.jromeo.backend.provision.service;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import com.jromeo.backend.provision.repository.ProvisionRepository;
import org.springframework.stereotype.Service;

@Service
public class ProvisionService {

    private final ProvisionRepository provisionRepository;

    public ProvisionService(ProvisionRepository provisionRepository) {
        this.provisionRepository = provisionRepository;
    }

    public void addGrocery(ProvisionEntity provision) {
        provisionRepository.save(provision);
    }
}
