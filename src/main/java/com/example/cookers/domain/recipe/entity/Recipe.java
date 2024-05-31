package com.example.cookers.domain.recipe.entity;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class Recipe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 적용
    private long id;

    @Column(length = 200)  // VARCHAR(200)
    private String recipe_title;

    @Column(columnDefinition = "TEXT") // TEXT 개념
    private String recipe_content;

    @ManyToOne
    private Member nickname;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
