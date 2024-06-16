package com.example.cookers.domain.recipe.service;

import com.example.cookers.DataNotFoundException;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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

    @Value("${custom.fileDirPath}")
    private String fileDirPath;

    public Recipe create (String title, String subject,String content, String nickname, String categoryValue, long hit,  MultipartFile thumbnail){
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
                .nickname(nickname)
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
            return recipe.get();
        } else {
            throw new DataNotFoundException("recipe not found");
        }
    }
}