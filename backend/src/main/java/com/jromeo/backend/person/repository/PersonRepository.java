package com.jromeo.backend.person.repository;

import com.jromeo.backend.person.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
}
