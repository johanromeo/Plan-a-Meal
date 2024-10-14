package com.jromeo.backend.recipe.repository;

import com.jromeo.backend.recipe.entity.RecipeEntity;
import com.jromeo.backend.recipe.service.RecipeService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for providing CRUD methods on {@link RecipeEntity} in MySQL database.
 * Implemented by {@link RecipeService}.
 *
 * @author Johan Romeo
 */
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {
}
