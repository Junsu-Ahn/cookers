package com.example.cookers.domain.comment.entity;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String content;
}
