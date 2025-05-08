package com.sun.sunproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.sunproject.entity.UserEntity;
import com.sun.sunproject.service.UserService;


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
    public boolean login(@RequestParam String userId, @RequestParam String userPw) {
        return userService.login(userId, userPw);
    }
}