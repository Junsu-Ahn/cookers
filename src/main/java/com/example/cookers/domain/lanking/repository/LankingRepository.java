package com.example.cookers.domain.lanking.repository;

import com.example.cookers.domain.lanking.entity.Lanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LankingRepository extends JpaRepository<Lanking, Long> {
}
