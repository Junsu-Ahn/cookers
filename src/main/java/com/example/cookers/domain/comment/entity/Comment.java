package com.example.cookers.domain.comment.entity;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.Column;
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
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String content;

    @JoinColumn
    @ManyToOne
    private Member author;
}
