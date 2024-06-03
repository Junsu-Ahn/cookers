package com.example.cookers.domain.recipe.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecipeCreateForm {

    @NotBlank(message="예 : 김치볶음밥 만들기")
    @Size(max=50, message = "제목을 50자 이내로 입력해주세요 !")
    private String main_title;

    @NotBlank(message="이 레시피에 대한 소개를 해주세요 ! 예) 어려서부터 맛있게 먹은 김치볶음밥을 다들 간단하고 맛있게 먹어주심을 바라며 레시피 공유드려요 !")
    @Size(max=1000, message = "제목을 1,000자 이내로 입력해주세요 !")
    private String recipe_presentation;
}