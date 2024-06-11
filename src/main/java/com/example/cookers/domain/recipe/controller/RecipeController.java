package com.example.cookers.domain.recipe.controller;

import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private final RecipeService recipeService;

//    @GetMapping("/list")
//    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page){
//        Page<Recipe> paging = this.recipeService.getList(page);
//        model.addAttribute("paging", paging);
////        List<Recipe> recipeList = recipeService.getList();
////
////        model.addAttribute("recipeList", recipeList);
//        return "recipe/list";
//    }

    @GetMapping("/recipe_create_form")
    public String Create(RecipeCreateForm recipeCreateForm){
        return "recipe/recipe_create_form";
    }

    @GetMapping("/list/{categoryValue}")
    public String categoryValue(@PathVariable("categoryValue")String categoryValue, Model model, @RequestParam(value="page", defaultValue="0") int page) {

        Page<Recipe> recipes = recipeService.getRecipesByCategoryValue(categoryValue, page);
        model.addAttribute("categoryValue", categoryValue);
        model.addAttribute("recipes", recipes);

        return "recipe/list";
    }
}
