package com.example.cookers.domain.recipe.entity;

import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    private String title; // 레시피 제목
    private String subject; // 레시피 소제목
    private String content; // 레시피 내용
    private String categoryValue; // 카테고리별 value값

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int hit; // 추천 수

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view; // 조회 수

    @Column(unique = true)
    private String nickname; // 닉네임
}
