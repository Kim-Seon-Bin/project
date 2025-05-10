package com.sun.sunproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.sunproject.entity.UserEntity;
import com.sun.sunproject.service.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 회원가입 처리
    @PostMapping("/join")
    public String join(@RequestBody UserEntity user) {
        userService.join(user);
        return "회원가입 성공!";
    }

    // 로그인 처리
    @PostMapping("/login")
    public boolean login(@RequestBody Map<String, String> loginData, HttpSession session) {
        String userId = loginData.get("userId");
        String userPw = loginData.get("userPw");
        
        // 로그인 인증 처리
        boolean loginSuccess = userService.login(userId, userPw);
        
        if (loginSuccess) {
            UserEntity user = userService.findByUserId(userId);
            session.setAttribute("user", user); 
        }
        return loginSuccess;
    }

    // 로그인 상태 확인
    @GetMapping("/checkLogin")
    public boolean checkLogin(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    // 사용자 정보
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            // 로그인 안 된 경우에도 userType: null 리턴
            return ResponseEntity.ok(Map.of(
                "userId", null,
                "userEmail", null,
                "userType", null
            ));
        }

        return ResponseEntity.ok(Map.of(
            "userId", user.getUserId(),
            "userEmail", user.getUserEmail(),
            "userType", user.getUserType()
        ));
    }
}