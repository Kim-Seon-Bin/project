package com.sun.sunproject.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="TB_FREELANCER")
public class FreelancerEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long freelancerIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_IDX")
    private UserEntity user;

    @Column(name="FREELANCER_NAME")
    private String freelancerName;

    @Column(name="FREELANCER_INTRO")
    private String freelancerIntro;
    
    @Column(name="FREELANCER_PORTFOLIO")
    private String freelancerPortfolio;

    @OneToMany(mappedBy="freelancer", fetch=FetchType.LAZY)
    private List<FreelancerSkillEntity> freelancerSkills;
}
