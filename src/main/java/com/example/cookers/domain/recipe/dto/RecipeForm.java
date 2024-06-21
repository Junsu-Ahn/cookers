package com.example.cookers.domain.recipe.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class RecipeForm {
    @NotEmpty(message = "제목은 필수 항목입니다.")
    private String title;

    @NotEmpty(message = "소제목은 필수 항목입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;

    @NotEmpty(message = "카테고리 선택은 필수 항목입니다.")
    private String categoryValue;

    private List<MakingStepForm> steps;

    @NotEmpty(message = "재료는 필수 항목입니다.")
    private List<String> ingredients;

    @NotEmpty(message = "재료 단위는 필수 항목입니다.")
    private List<String> ingredientUnits;

    @NotEmpty(message = "양념은 필수 항목입니다.")
    private List<String> seasonings;

    @NotEmpty(message = "양념 단위는 필수 항목입니다.")
    private List<String> seasoningUnits;

    @NotNull(message = "요리 난이도는 필수 항목입니다.")
    @Min(value = 1, message = "난이도는 최소 1이어야 합니다.")
    @Max(value = 5, message = "난이도는 최대 5이어야 합니다.")
    private Integer recipeLevel;
}