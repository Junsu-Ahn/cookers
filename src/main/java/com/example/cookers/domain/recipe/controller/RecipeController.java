package com.example.cookers.domain.recipe.controller;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import com.example.cookers.domain.member.service.MemberService;
import com.example.cookers.domain.recipe.dto.MakingStepForm;
import com.example.cookers.domain.recipe.dto.RecipeForm;
import com.example.cookers.domain.recipe.entity.Ingredient;
import com.example.cookers.domain.recipe.entity.MakingStep;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.entity.Seasoning;
import com.example.cookers.domain.recipe.repository.RecipeRepository;
import com.example.cookers.domain.recipe.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    @Value("${custom.fileDirPath}")
    private String fileDirPath;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final RecipeRepository recipeRepository;

    @Autowired
    private final RecipeService recipeService;

    @Autowired
    private final MemberService memberService;


    // 추천수 만들기
    @PostMapping("/recommend")
    @ResponseBody
    public ResponseEntity<?> recommendRecipe(@RequestParam("id") Long recipeId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후 이용 가능합니다");
        }
        String username = principal.getName();
        Long updatedHitCount = recipeService.toggleRecommendCount(recipeId, username);
        return ResponseEntity.ok(Map.of("hit", updatedHitCount));
    }

    @GetMapping("/userRecipesCount")
    @ResponseBody
    public ResponseEntity<?> getUserRecipesCount(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후 이용 가능합니다");
        }
        String username = principal.getName();
        long recipeCount = recipeService.countUserRecipes(username);
        return ResponseEntity.ok(Map.of("recipeCount", recipeCount));
    }

    @GetMapping("/userRecipeHits")
    @ResponseBody
    public ResponseEntity<?> getUserRecipeHits(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후 이용 가능합니다");
        }
        String username = principal.getName();
        long totalHits = recipeService.sumUserRecipeHits(username);
        return ResponseEntity.ok(Map.of("totalHits", totalHits));
    }

    // 레시피 디테일
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Recipe recipe = this.recipeService.getRecipe(id);
        model.addAttribute("recipe", recipe);
        return "recipe/recipe_detail";
    }

    //레시피 등록하기(Create)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/recipe_create_form")
    public String createForm(Model model) {
        model.addAttribute("recipeForm", new RecipeForm());
        return "recipe/recipe_create_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/recipe_create_form")
    public String createRecipe(@Valid @ModelAttribute RecipeForm recipeForm,
                               @RequestParam("thumbnail") MultipartFile thumbnail,
                               @RequestParam(value = "stepImages", required = false) List<MultipartFile> stepImages,
                               BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "recipe/recipe_create_form";
        }

        Member member = memberService.getMember(principal.getName());
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeForm.getTitle());
        recipe.setSubject(recipeForm.getSubject());
        recipe.setContent(recipeForm.getContent());
        recipe.setCategoryValue(recipeForm.getCategoryValue());
        recipe.setRecipeLevel(recipeForm.getRecipeLevel());  // 여기서 값 설정
        recipe.setAuthor(member);
        recipe.setHit(0L); // 초기 hit 값 설정
        recipe.setView(0); // 초기 view 값 설정

        // 썸네일 이미지 처리
        if (thumbnail != null && !thumbnail.isEmpty()) {
            try {
                String originalFilename = thumbnail.getOriginalFilename();
                String newFilename = UUID.randomUUID().toString() + "_" + originalFilename;
                Path imagePath = Paths.get(fileDirPath, newFilename);
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, thumbnail.getBytes());
                recipe.setFilename(originalFilename);
                recipe.setFilepath("/file/" + newFilename); // WebMvcConfig 설정과 일치하도록 수정

                // 로그 출력
                System.out.println("Thumbnail saved at: " + imagePath.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return "recipe/recipe_create_form";
            }
        }

        // 요리 단계 처리
        List<MakingStep> steps = new ArrayList<>();
        for (int i = 0; i < recipeForm.getSteps().size(); i++) {
            MakingStepForm stepForm = recipeForm.getSteps().get(i);
            MakingStep step = new MakingStep();
            step.setStepNumber(stepForm.getStepNumber());
            step.setStepText(stepForm.getStepText());
            step.setStepTipText(stepForm.getStepTipText());

            // 단계 이미지 처리
            if (stepImages != null && stepImages.size() > i && !stepImages.get(i).isEmpty()) {
                try {
                    String originalFilename = stepImages.get(i).getOriginalFilename();
                    String newFilename = UUID.randomUUID().toString() + "_" + originalFilename;
                    Path imagePath = Paths.get(fileDirPath, newFilename);
                    Files.createDirectories(imagePath.getParent());
                    Files.write(imagePath, stepImages.get(i).getBytes());
                    step.setImageFilename(originalFilename);
                    step.setImageFilePath("/file/" + newFilename); // WebMvcConfig 설정과 일치하도록 수정

                    // 로그 출력
                    System.out.println("Step image saved at: " + imagePath.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    return "recipe/recipe_create_form";
                }
            }

            steps.add(step);
        }

        // 재료 처리
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < recipeForm.getIngredients().size(); i++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(recipeForm.getIngredients().get(i));
            ingredient.setUnit(recipeForm.getIngredientUnits().get(i));
            ingredients.add(ingredient);
        }

        // 양념 처리
        List<Seasoning> seasonings = new ArrayList<>();
        for (int i = 0; i < recipeForm.getSeasonings().size(); i++) {
            Seasoning seasoning = new Seasoning();
            seasoning.setName(recipeForm.getSeasonings().get(i));
            seasoning.setUnit(recipeForm.getSeasoningUnits().get(i));
            seasonings.add(seasoning);
        }

        recipeService.saveRecipe(recipe, steps, ingredients, seasonings);
        return "redirect:/"; // 메인화면으로 리다이렉트
    }

    // 카테고리별로 리스트 확인하기
    @GetMapping("/list/{categoryValue}")
    public String recipePage(@PathVariable("categoryValue") String categoryValue, Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Recipe> recipes = recipeService.getRecipesByCategoryValue(categoryValue, page);
        model.addAttribute("categoryValue", categoryValue);
        model.addAttribute("recipes", recipes);
        return "recipe/list";
    }

    @GetMapping("/list")
    public String listRecipes(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Recipe> recipes = recipeService.findAllRecipes(page);
        model.addAttribute("recipes", recipes);
        return "recipe/list";
    }

    @GetMapping("/search")
    public String searchRecipes(@RequestParam("keyword") String keyword, Model model,
                                @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Recipe> recipes = recipeService.searchRecipes(keyword, page);
        model.addAttribute("recipes", recipes);
        model.addAttribute("keyword", keyword);
        return "recipe/list";
    }

    // 레시피 수정하기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String recipeModify(@Valid RecipeForm recipeForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "recipe_form";
        }
        Recipe recipe = this.recipeService.getRecipe(id);
        if (!recipe.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.recipeService.modify(recipe, recipeForm.getSubject(), recipeForm.getContent());
        return String.format("redirect:/recipe/detail/%s", id);
    }

    // 레시피 삭제하기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String recipeDelete(Principal principal, @PathVariable("id") Long id) {
        Recipe recipe = this.recipeService.getRecipe(id);
        if (!recipe.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.recipeService.delete(recipe);
        return "redirect:/";
    }

}