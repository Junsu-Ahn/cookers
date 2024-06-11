package com.example.cookers.domain.recipe.service;

import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    @Autowired
    private final RecipeRepository recipeRepository;

    public Page<Recipe> getList(int page){
        Pageable pageable = PageRequest.of(page,10);
        return this.recipeRepository.findAll(pageable);
    }

    public List<Recipe> getList() {
        return recipeRepository.findAll();
    }

    public void create (String title, String subject,String content, String nickname, String categoryValue){
        Recipe recipe = Recipe.builder()
                .title(title)
                .subject(subject)
                .content(content)
                .nickname(nickname)
                .createDate(LocalDateTime.now())
                .categoryValue(categoryValue)
                .build();
        recipeRepository.save(recipe);
    }

    public Page<Recipe> getRecipesByCategoryValue(String categoryValue, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return recipeRepository.findByCategoryValue(categoryValue, pageable);
    }
}