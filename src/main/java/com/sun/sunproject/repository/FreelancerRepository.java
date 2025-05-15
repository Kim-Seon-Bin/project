package com.sun.sunproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.FreelancerEntity;
import com.sun.sunproject.entity.UserEntity;

@Repository
public interface FreelancerRepository extends JpaRepository<FreelancerEntity, Long>{
    @EntityGraph(attributePaths={"freelancerSkills", "freelancerSkills.skill"})
    List<FreelancerEntity> findAll();
    
    FreelancerEntity findByUser(UserEntity user);
    FreelancerEntity findByUser_UserIdx(Long userIdx);
}
