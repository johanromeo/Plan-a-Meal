package com.jromeo.backend.person.repository;

import com.jromeo.backend.person.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Person repository.
 *
 * @author Johan Romeo
 */
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
}
