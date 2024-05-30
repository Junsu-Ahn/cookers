package com.example.cookers.domain.recipe.service;

import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public List<Recipe> getList() {
        return recipeRepository.findAll();
    }

    public void create ( String recipe_title, String recipe_content, String nickname){
        Recipe recipe = Recipe.builder()
                .recipe_title(recipe_title)
                .recipe_content(recipe_content)
                .nickname(nickname)
                .createDate(LocalDateTime.now())
                .build();
        recipeRepository.save(recipe);
    }
}