package com.example.cookers.domain.recipe.repository;

import com.example.cookers.domain.recipe.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
