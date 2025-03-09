package com.example.cookers.domain.recipe.entity;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.global.jpa.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
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
public class RecipeRecommendation extends BaseEntity {

    @ManyToOne
    private Member member;

    @ManyToOne
    private Recipe recipe;
}



















