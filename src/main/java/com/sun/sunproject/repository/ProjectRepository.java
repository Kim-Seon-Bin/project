package com.sun.sunproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>{
    @EntityGraph(attributePaths= {"client", "projectSkills", "projectSkills.skill"})
    List<ProjectEntity> findAll();
}
