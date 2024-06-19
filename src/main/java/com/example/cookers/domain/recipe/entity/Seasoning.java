package com.example.cookers.domain.recipe.entity;

import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seasoning extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private String name;
    private String unit;
}