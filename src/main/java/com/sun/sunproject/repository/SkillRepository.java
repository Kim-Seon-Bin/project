package com.sun.sunproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.SkillEntity;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long>{
    Optional<SkillEntity> findBySkillName(String skillName);
}
