package com.example.cookers.domain.recipe.controller;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.service.MemberService;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private final RecipeService recipeService;

    @Autowired
    private final MemberService memberService;

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Recipe recipe = this.recipeService.getRecipe(id);
        model.addAttribute("recipe", recipe);

        return "recipe/recipe_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/recipe_create_form")
    // QuestionForm 변수는 model.addAttribute 없이 바로 뷰에서 접근할 수 있다.
    // QuestionForm questionForm 써주는 이유 : question_form.html에서 questionForm 변수가 없으면 실행이 안되기 때문에
    // 빈 객체라도 만든다.
    public String Create(RecipeCreateForm recipeCreateForm) {
        return "recipe/recipe_create_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/recipe_create_form")
    // QuestionForm 값을 바인딩할 때 유효성 체크를 해라 !!
    // QuestionForm 변수는 model.addAttribute 없이 바로 뷰에서 접근할 수 있다.
    public String recipeCreate(@Valid RecipeCreateForm recipeCreateForm , BindingResult bindingResult, Principal principal,@RequestParam("thumbnail") MultipartFile thumbnail ) {
//        if ( bindingResult.hasErrors() ) {
//            // recipe_create_form.html 실행
//            // 다시 작성하라는 의미로 응답에 폼을 실어서 보냄
//            return "recipe/recipe_create_form";
//        }
        Optional<Member> member = this.memberService.findByUsername(principal.getName());
        Recipe recipe = recipeService.create(recipeCreateForm.getTitle(),recipeCreateForm.getSubject(), recipeCreateForm.getContent(),recipeCreateForm.getNickname() ,recipeCreateForm.getCategoryValue(), 0, thumbnail);

        return "redirect:/"; // 레시피 등록 후 메인화면으로 복귀
    }

    @GetMapping("/list/{categoryValue}")
    public String recipePage(@PathVariable("categoryValue")String categoryValue, Model model, @RequestParam(value="page", defaultValue="0") int page) {

        Page<Recipe> recipes = recipeService.getRecipesByCategoryValue(categoryValue, page);
        model.addAttribute("categoryValue", categoryValue);
        model.addAttribute("recipes", recipes);

        return "recipe/list";
    }
}
