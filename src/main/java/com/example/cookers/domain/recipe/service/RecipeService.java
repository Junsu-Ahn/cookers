package com.example.cookers.domain.recipe.service;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.RecursiveAction;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public List<Recipe> getList() {
        return recipeRepository.findAll();
    }

    public Recipe create ( String recipe_title, String recipe_content, Member nickname){
        Recipe recipe = new Recipe();
        recipe.setRecipe_title(recipe_title);
        recipe.setRecipe_content(recipe_content);
        recipe.setNickname(nickname);
       return recipeRepository.save(recipe);
    }
}