package com.sun.sunproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_USER")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="USER_IDX")
    private Long userIdx;

    @Column(name="USER_ID", nullable=false, unique=true)
    private String userId;

    @Column(name="USER_PW", nullable=false)
    private String userPw;

    @Column(name="USER_EMAIL", nullable=false, unique=true)
    private String userEmail;

    @Column(name="USER_TYPE", nullable=false)
    private String userType;
}