package com.sun.sunproject.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Table(name="TB_PROJECT_APPLICATION")
public class ProjectApplicationEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "APPLICATION_IDX")
    private Long projectApplicationIdx;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FREELANCER_IDX")
    private FreelancerEntity freelancer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROJECT_IDX")
    private ProjectEntity project;

    @Column(name="PROJECT_APPLICATION_TIME")
    private LocalDateTime time;
}
