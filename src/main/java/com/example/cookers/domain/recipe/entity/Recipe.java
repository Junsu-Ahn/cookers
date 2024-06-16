package com.example.cookers.domain.recipe.entity;

import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
public class Recipe extends BaseEntity {

    @Column(unique = true)
    private String title; // 레시피 제목

    private String subject; // 레시피 소제목

    private String content; // 레시피 내용

    private String categoryValue; // 카테고리별 value값

    private int recipeLevel; // 레시피의 난이도

    private String thumnailImg;

    @Column(nullable = false)
    private Long hit; // 추천 수

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view; // 조회 수

    @Column(unique = true)
    private String nickname; // 닉네임

    private String filename; // 파일이름
    private String filepath; // 파일경로
}
