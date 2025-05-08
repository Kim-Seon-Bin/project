package com.sun.sunproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.SkillEntity;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long>{

}
