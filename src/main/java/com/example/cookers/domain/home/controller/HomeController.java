package com.example.cookers.domain.home.controller;

import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RecipeService recipeService;

    @GetMapping("/")
    public String home(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<Recipe> popularRecipes = recipeService.getPopularRecipes(pageable);
        Page<Recipe> latestRecipes = recipeService.getLatestRecipes(pageable);

        model.addAttribute("popularRecipes", popularRecipes);
        model.addAttribute("latestRecipes", latestRecipes);

        return "home/main";
    }
}