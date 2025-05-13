package com.sun.sunproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sun.sunproject.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    @Query("SELECT c FROM ClientEntity c WHERE c.user.userId = :userId")
    ClientEntity findByUserId(@Param("userId") String userId);
}
