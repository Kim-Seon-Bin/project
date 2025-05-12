package com.sun.sunproject.entity;

import java.time.LocalDateTime;
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
@Table(name="TB_PROJECT")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long projectIdx;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_IDX")
    private ClientEntity client;

    @Column(name="PROJECT_TITLE")
    private String projectTitle;

    @Column(name="PROJECT_INFO")
    private String projectInfo;

    @Column(name="PROJECT_BUDGET")
    private String projectBudget;

    @Column(name="PROJECT_PERIOD")
    private String projectPeriod;

    @Column(name="PROJECT_REGISTRATION_TIME")
    private LocalDateTime time;

    @OneToMany(mappedBy="project", fetch=FetchType.LAZY)
    private List<ProjectSkillEntity> projectSkills;
}
