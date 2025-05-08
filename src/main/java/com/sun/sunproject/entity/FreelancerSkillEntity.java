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
@Table(name="TB_FREELANCER_SKILL")
public class FreelancerSkillEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long freelancerSkillIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="FREELANCER_IDX")
    private FreelancerEntity freelancer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SKILL_IDX")
    private SkillEntity skill;
}
