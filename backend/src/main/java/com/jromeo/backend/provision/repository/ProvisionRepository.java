package com.jromeo.backend.provision.repository;

import com.jromeo.backend.provision.entity.ProvisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvisionRepository extends JpaRepository<ProvisionEntity, Integer> {

    ProvisionEntity findByName(String name);

}
