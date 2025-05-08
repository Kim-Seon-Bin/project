package com.sun.sunproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    UserEntity findByUserId(String userId);
    UserEntity findByUserEmail(String userEmail);
}