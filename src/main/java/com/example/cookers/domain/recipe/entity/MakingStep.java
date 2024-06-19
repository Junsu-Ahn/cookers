package com.example.cookers.domain.recipe.entity;

import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class MakingStep extends BaseEntity {

    private String stepText; // 순서별 텍스트

    private String stepTipText; // 팁 박스 텍스트

    private String imageFilename; // 스탭별 이미지 이름

    private String imageFilePath; // 스탭별 이미지 경로

    private int stepNumber; // 스탭 순서

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe; // 레시피와의 관계
}
