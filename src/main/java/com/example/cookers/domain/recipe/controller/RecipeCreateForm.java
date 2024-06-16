package com.example.cookers.domain.recipe.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecipeCreateForm {

    @NotBlank(message="예 : 김치볶음밥 만들기")
    @Size(max=20, message = "제목을 20자 이내로 입력해주세요 !")
    private String title; // 레시피 제목

    @NotBlank(message="예 : 한국인의 밥심 !")
    @Size(max=30, message = "소제목을 30자 이내로 입력해주세요 !")
    private String subject; // 레시피 소제목

    @NotBlank(message="이 레시피에 대한 소개를 해주세요 ! 예) 어려서부터 맛있게 먹은 김치볶음밥을 다들 간단하고 맛있게 먹어주심을 바라며 레시피 공유드려요 !")
    @Size(max=1000, message = "제목을 1,000자 이내로 입력해주세요 !")
    private String content; // 레시피 내용

    @NotBlank(message="작성자 !")
    private String nickname; // 작성자

    @NotBlank(message="사람들이 찾기 편하도록 카테고리를 분류해주세요 !")
    private String categoryValue; // 카테고리별 value값

    private Long hit; // 추천 수

}