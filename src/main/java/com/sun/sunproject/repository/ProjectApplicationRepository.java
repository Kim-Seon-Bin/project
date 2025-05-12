package com.sun.sunproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.ProjectApplicationEntity;

@Repository
public interface ProjectApplicationRepository extends JpaRepository<ProjectApplicationEntity, Long>{

}
