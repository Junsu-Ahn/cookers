package com.example.cookers.domain.recipe.repository;

import com.example.cookers.domain.recipe.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Page<Recipe> findAll(Pageable pageable);

    Page<Recipe> findByCategoryValue(String categoryValue, Pageable pageable);
    List<Recipe> findByNickname(String nickname);

    @Query("SELECT r FROM Recipe r WHERE r.title LIKE %:keyword% OR r.content LIKE %:keyword%")
    Page<Recipe> searchByTitleOrContent(@Param("keyword") String keyword, Pageable pageable);

    Page<Recipe> findTop10ByOrderByHitDesc(Pageable pageable);
    Page<Recipe> findTop10ByOrderByCreateDateDesc(Pageable pageable);

    @Query("SELECT COALESCE(SUM(r.hit), 0) FROM Recipe r WHERE r.author.id = :authorId")
    Long sumHitsByAuthor(@Param("authorId") Long authorId);

    List<Recipe> findByAuthorNickname(String nickname);

}
