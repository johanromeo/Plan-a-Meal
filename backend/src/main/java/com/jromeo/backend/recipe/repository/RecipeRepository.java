package com.jromeo.backend.recipe.repository;

import com.jromeo.backend.recipe.entity.RecipeResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeResponseEntity, Integer> {

}
