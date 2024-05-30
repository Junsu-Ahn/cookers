package com.example.cookers.domain.recipe.entity;

import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
public class Recipe extends BaseEntity {
    private long id;
    @Column(length = 200)  // VARCHAR(200)
    private String recipe_title;

    @Column(columnDefinition = "TEXT") // TEXT 개념
    private String recipe_content;

    @Column(unique = true) // TEXT 개념
    private String nickname;

    private LocalDateTime createDate;
}
