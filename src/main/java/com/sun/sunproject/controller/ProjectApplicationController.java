package com.sun.sunproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.sunproject.dto.ProjectApplicationDto;
import com.sun.sunproject.entity.UserEntity;
import com.sun.sunproject.service.ProjectApplicationService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/application")
public class ProjectApplicationController {
    @Autowired
    private ProjectApplicationService projectApplicationService;
    
    @PostMapping("/apply")
    public ResponseEntity<?> applyProject(@RequestBody ProjectApplicationDto dto, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        try {
            projectApplicationService.saveApplicationForFreelancer(user, dto.getProjectIdx());
            return ResponseEntity.ok("지원이 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }
}