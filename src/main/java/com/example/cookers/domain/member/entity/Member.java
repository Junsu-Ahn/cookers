package com.example.cookers.domain.member.entity;

import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.entity.RecipeRecommendation;
import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member extends BaseEntity {

    @Comment("유저 아이디")
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String nickname;
    private String email;
    private String providerTypeCode;
    private String profile_url;

    // provider : google이 들어감
    private String provider;

    // providerId : 구굴 로그인 한 유저의 고유 ID가 들어감
    private String providerId;

    private Long hit;
}
