package com.jromeo.backend.recipe.repository;

import com.jromeo.backend.recipe.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {

}
