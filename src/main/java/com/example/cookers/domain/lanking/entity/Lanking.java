package com.example.cookers.domain.lanking.entity;

import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class Lanking extends BaseEntity {
}
