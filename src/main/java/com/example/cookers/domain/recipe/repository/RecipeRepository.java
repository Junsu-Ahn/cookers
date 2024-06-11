package com.example.cookers.domain.recipe.repository;

import com.example.cookers.domain.recipe.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Page<Recipe> findAll(Pageable pageable);

    Page<Recipe> findByCategoryValue(String categoryValue, Pageable pageable);

}
