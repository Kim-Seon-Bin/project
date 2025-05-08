package com.sun.sunproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.ProjectSkillEntity;

@Repository
public interface ProjectSkillRepository extends JpaRepository<ProjectSkillEntity, Long>{

}
