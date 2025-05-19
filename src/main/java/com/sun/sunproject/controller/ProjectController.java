package com.sun.sunproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.sunproject.dto.FreelancerDto;
import com.sun.sunproject.dto.ProjectDetailDto;
import com.sun.sunproject.dto.ProjectDto;
import com.sun.sunproject.entity.UserEntity;
import com.sun.sunproject.service.ProjectService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService  projectService;

    @GetMapping("/api/projects")
    public List<ProjectDto> getProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/api/projects/{id}")
    public ProjectDetailDto getProjectDetail(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("프로젝트 ID가 누락되었습니다.");
        }
        return projectService.getProjectDetail(id);
    }

    @PostMapping("/api/projects")
    public void registerProject(@RequestBody ProjectDetailDto dto, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("로그인 정보가 없습니다.");
        }
        String userId = user.getUserId(); // 세션에서 꺼냄
        projectService.registerProject(userId, dto);
        }

    @GetMapping("/api/projects/by-client")
    public List<ProjectDto> getClientProjects(@RequestParam String userId) {
        return projectService.getProjectsByUserId(userId);
    }

    @GetMapping("/api/projects/{projectId}/applicants")
    public List<FreelancerDto> getApplicantsByProject(@PathVariable Long projectId) {
        return projectService.getApplicantsByProject(projectId);
    }
}
