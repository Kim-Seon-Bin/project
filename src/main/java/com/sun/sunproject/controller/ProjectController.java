package com.sun.sunproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sun.sunproject.dto.ProjectDetailDto;
import com.sun.sunproject.dto.ProjectDto;
import com.sun.sunproject.service.ProjectService;

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
        return projectService.getProjectDetail(id);
    }
}
