package com.example.cookers.domain.recipe.service;


import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import com.example.cookers.domain.recipe.entity.*;
import com.example.cookers.domain.recipe.repository.*;
import com.example.cookers.global.DataNotFoundException;
import com.example.cookers.global.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeService {
    @Autowired
    private final RecipeRepository recipeRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final MakingStepRepository makingStepRepository;
    @Autowired
    private final IngredientRepository ingredientRepository;
    @Autowired
    private final SeasoningRepository seasoningRepository;
    @Autowired
    private final RecipeRecommendationRepository recipeRecommendationRepository;

    @Value("${custom.fileDirPath}")
    private String fileDirPath;

    @Transactional
        public Long toggleRecommendCount(Long recipeId, String username) {
            Recipe recipe = recipeRepository.findById(recipeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));
            Member member = memberRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

            Optional<RecipeRecommendation> recommendationOpt = recipeRecommendationRepository.findByMemberAndRecipe(member, recipe);

            if (recommendationOpt.isPresent()) {
            recipeRecommendationRepository.deleteByMemberAndRecipe(member, recipe);
            recipe.setHit(recipe.getHit() - 1);
        } else {
            RecipeRecommendation recommendation = new RecipeRecommendation();
            recommendation.setMember(member);
            recommendation.setRecipe(recipe);
            recipeRecommendationRepository.save(recommendation);
            recipe.setHit(recipe.getHit() + 1);
        }

        recipeRepository.save(recipe);
        return recipe.getHit();
    }

    @Transactional(readOnly = true)
    public long countUserRecipes(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        return recipeRecommendationRepository.countByAuthor(member);
    }

    @Transactional(readOnly = true)
    public long sumUserRecipeHits(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        Long sumHits = recipeRecommendationRepository.sumHitByAuthor(member);
        return sumHits != null ? sumHits : 0; // null일 경우 0으로 설정
    }

    public Recipe create (String title, String subject, String content, Member author, String categoryValue, long hit, MultipartFile thumbnail){
        String thumbnailRelPath = "post/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(fileDirPath + "/" + thumbnailRelPath);

        try {
            if (!thumbnailFile.getParentFile().exists()) {
                thumbnailFile.getParentFile().mkdirs();
            }
            thumbnail.transferTo(thumbnailFile);
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }

        Recipe recipe = Recipe.builder()
                .title(title)
                .subject(subject)
                .content(content)
                .author(author)
                .createDate(LocalDateTime.now())
                .categoryValue(categoryValue)
                .hit(hit)
                .thumnailImg(thumbnailRelPath)
                .build();
        return recipeRepository.save(recipe);
    }

    public Page<Recipe> getRecipesByCategoryValue(String categoryValue, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return recipeRepository.findByCategoryValue(categoryValue, pageable);
    }

    public Recipe getRecipe(Long id) {
        Optional<Recipe> recipe = this.recipeRepository.findById(id);
        if (recipe.isPresent()) {
            Recipe recipe1 = recipe.get();
            recipe1.setView(recipe1.getView()+1);
            this.recipeRepository.save(recipe1);
            return recipe1;
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    @Transactional
    public Recipe saveRecipe(Recipe recipe, List<MakingStep> steps, List<Ingredient> ingredients, List<Seasoning> seasonings) {
        Recipe savedRecipe = recipeRepository.save(recipe);

        for (MakingStep step : steps) {
            step.setRecipe(savedRecipe);
            makingStepRepository.save(step);
        }

        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(savedRecipe);
            ingredientRepository.save(ingredient);
        }

        for (Seasoning seasoning : seasonings) {
            seasoning.setRecipe(savedRecipe);
            seasoningRepository.save(seasoning);
        }

        return savedRecipe;
    }

    @Transactional(readOnly = true)
    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

    public Page<Recipe> findAllRecipes(int page) {
        Pageable pageable = PageRequest.of(page, 10); // 페이지 당 10개의 항목, 필요한 경우 조정 가능
        return recipeRepository.findAll(pageable);
    }

    public Page<Recipe> searchRecipes(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return recipeRepository.searchByTitleOrContent(keyword, pageable);
    }

    // 레시피 수정하기
    public void modify(Recipe recipe,String title, String subject, String content, String categoryValue, int recipeLevel) {
        recipe.setTitle(title);
        recipe.setSubject(subject);
        recipe.setContent(content);
        recipe.setModifyDate(LocalDateTime.now());
        recipe.setCategoryValue(categoryValue);
        recipe.setRecipeLevel(recipeLevel);
        this.recipeRepository.save(recipe);
    }

    // 레시피 삭제하기
    public void delete(Recipe recipe) {
        this.recipeRepository.delete(recipe);
    }

}