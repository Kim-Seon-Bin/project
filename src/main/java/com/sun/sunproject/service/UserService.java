package com.sun.sunproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.sunproject.entity.UserEntity;
import com.sun.sunproject.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // user 정보 DB 저장
    public void join(UserEntity user) {
        userRepository.save(user);
    }

    // user ID를 찾고, 비밀번호 일치 확인
    public boolean login(String userId, String userPw) {
        UserEntity user = userRepository.findByUserId(userId);
        return user != null && user.getUserPw().equals(userPw);
    }

    // userId로 사용자 정보 조회
    public UserEntity findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}