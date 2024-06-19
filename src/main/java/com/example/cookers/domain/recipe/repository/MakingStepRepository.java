package com.example.cookers.domain.recipe.repository;

import com.example.cookers.domain.recipe.entity.MakingStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakingStepRepository extends JpaRepository<MakingStep, Long> {
}