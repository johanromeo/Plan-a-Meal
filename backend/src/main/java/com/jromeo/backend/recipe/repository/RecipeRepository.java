package com.jromeo.backend.openai.chatgpt.recipe.repository;

import com.jromeo.backend.openai.chatgpt.recipe.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {
}
