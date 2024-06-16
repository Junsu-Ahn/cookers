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
public class ingredientsAndSeasoning extends BaseEntity {

    private String ingredientsName; // 재료 이름

    private String ingredientsSize; // 재료 무게

    private String SeasoningName; // 양념 이름

    private String SeasoningSize; // 양념 무게

    private int ingredientsStepNumber; // 식자재 스탭 순서

    private int SeasoningStepNumber; // 양념 스탭 순서

    private String title; // 레시피 제목 -> recipe와 join하기 위해 사용
}
