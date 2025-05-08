package com.sun.sunproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="TB_PROJECT_SKILL")
public class ProjectSkillEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long projectSkillIdx;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROJECT_IDX")
    private ProjectEntity project;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SKILL_IDX")
    private SkillEntity skill;
}
