package com.example.cookers.domain.recipe.repository;

import com.example.cookers.domain.recipe.entity.Seasoning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasoningRepository extends JpaRepository<Seasoning, Long> {
}
