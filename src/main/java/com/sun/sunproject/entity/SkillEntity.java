package com.sun.sunproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="TB_SKILL")
public class SkillEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long skillIdx;

    @Column(name="SKILL_NAME")
    private String skillName;
}
