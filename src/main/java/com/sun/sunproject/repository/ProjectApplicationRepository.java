package com.sun.sunproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.FreelancerEntity;
import com.sun.sunproject.entity.ProjectApplicationEntity;
import com.sun.sunproject.entity.ProjectEntity;

@Repository
public interface ProjectApplicationRepository extends JpaRepository<ProjectApplicationEntity, Long>{
    List<ProjectApplicationEntity> findByFreelancer(FreelancerEntity freelancer);
    List<ProjectApplicationEntity> findByProject(ProjectEntity project);
}
