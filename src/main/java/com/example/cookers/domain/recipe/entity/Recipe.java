package com.example.cookers.domain.recipe.entity;

import com.example.cookers.domain.comment.entity.Comment;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MakingStep> steps; // 요리 단계

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients; // 재료

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seasoning> seasonings; // 양념

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Column(nullable = false)
    private Long hit = 0L; // 추천 수, 기본값 설정

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view; // 조회 수

    @JoinColumn
    @ManyToOne
    private Member author;

    private String filename; // 파일이름
    private String filepath; // 파일경로
}
