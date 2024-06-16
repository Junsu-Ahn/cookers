package com.example.cookers.domain.recipe.entity;

import com.example.cookers.global.jpa.BaseEntity;
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
public class makingStep extends BaseEntity {

    private String stepText; // 순서별 텍스트

    private String stepTipText; // 팁 박스 텍스트

    private String imageFilename; // 스탭별 이미지 이름

    private String imageFilePath; // 스탭별 이미지 경로

    private String title; // 레시피 제목 -> recipe와 join하기 위해 사용

    private int stepNumber; // 스탭 순서
}
