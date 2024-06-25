package com.example.cookers.domain.recipe.repository;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.entity.RecipeRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecipeRecommendationRepository extends JpaRepository<RecipeRecommendation, Long> {
    Optional<RecipeRecommendation> findByMemberAndRecipe(Member member, Recipe recipe);
    void deleteByMemberAndRecipe(Member member, Recipe recipe);

    @Query("SELECT COUNT(r) FROM Recipe r WHERE r.author = :author")
    long countByAuthor(@Param("author") Member author);

    @Query("SELECT SUM(r.hit) FROM Recipe r WHERE r.author = :author")
    Long sumHitByAuthor(@Param("author") Member author); // 반환 타입을 Long으로 변경

    @Query("SELECT COUNT(r) FROM RecipeRecommendation r WHERE r.member.id = :memberId")
    long countByMember(@Param("memberId") Long memberId);

    @Query("SELECT COUNT(r) FROM RecipeRecommendation r")
    long countTotalRecommendations();

}
