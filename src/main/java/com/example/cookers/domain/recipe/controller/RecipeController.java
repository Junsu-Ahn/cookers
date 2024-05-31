package com.example.cookers.domain.recipe.controller;

import com.example.cookers.domain.member.service.MemberService;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model){
        List<Recipe> recipeList = recipeService.getList();

        model.addAttribute("recipeList", recipeList);
        return "recipe/list";
    }

    @GetMapping("/recipe_create_form")
    public String Create(RecipeCreateForm recipeCreateForm){
        return "recipe/recipe_create_form";
    }

//    @GetMapping("/recipe_create_form")
//    public String recipeCreate(@Valid RecipeCreateForm recipeCreateForm, BindingResult bindingResult, Principal principal){
//        if( bindingResult.hasErrors() ){
//            return "recipe/recipe_create_form";
//        }
//        Member member = this.memberService.getMember(principal.getName());
//        Recipe recipe = this.recipeService.create(recipeCreateForm.getMain_title(), recipeCreateForm.getRecipe_presentation(), member);
//
//        return "redirect:/recipe/list";
//    }
}
