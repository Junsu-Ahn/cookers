package com.example.cookers.domain.ranking.entity;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
public class Ranking extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Long hit;
}
