package com.sun.sunproject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.sunproject.dto.FreelancerDto;
import com.sun.sunproject.dto.MyApplicationDto;
import com.sun.sunproject.entity.UserEntity;
import com.sun.sunproject.service.FreelancerService;
import com.sun.sunproject.service.ProjectApplicationService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FreelancerController {
    private final FreelancerService freelancerService;
    private final ProjectApplicationService projectApplicationService;

    @GetMapping("/api/freelancers")
    public List<FreelancerDto> getFreelancers() {
        return freelancerService.getAllFreelancers();
    }

    @PostMapping("/api/freelancers")
    public ResponseEntity<?> createFreelancer(@RequestBody FreelancerDto dto, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        System.out.println("프리랜서 등록 요청 - 사용자 ID: " + user.getUserId());
        System.out.println("DTO 내용: " + dto);

        try {
            freelancerService.createFreelancer(dto, user);
            return ResponseEntity.ok("프리랜서 등록 성공");
        } catch (Exception e) {
            e.printStackTrace(); // 콘솔 로그 확인 필수
            return ResponseEntity.status(500).body("서버 오류 발생: " + e.getMessage());
        }
    }

    // 마이페이지 프로젝트 조회
    @GetMapping("/api/mypage/applications")
    public ResponseEntity<?> getMyApplications(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        try {
            List<MyApplicationDto> applications = projectApplicationService.getApplicationsByUser(user);
            return ResponseEntity.ok(applications);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }
}
