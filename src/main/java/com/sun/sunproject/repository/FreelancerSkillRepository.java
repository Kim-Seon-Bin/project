package com.sun.sunproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.FreelancerSkillEntity;

@Repository
public interface FreelancerSkillRepository extends JpaRepository<FreelancerSkillEntity, Long>{

}
