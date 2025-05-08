package com.sun.sunproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sun.sunproject.dto.ProjectDetailDto;
import com.sun.sunproject.dto.ProjectDto;
import com.sun.sunproject.entity.ProjectEntity;
import com.sun.sunproject.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream().map(p -> {
            List<String> skills = p.getProjectSkills().stream()
                .map(ps -> ps.getSkill().getSkillName())
                .collect(Collectors.toList());

            return new ProjectDto(
                p.getProjectIdx(),
                p.getClient().getClientName(),
                p.getProjectTitle(),
                skills
            );
        }).collect(Collectors.toList());
    }

    public ProjectDetailDto getProjectDetail(Long projectId) {
        ProjectEntity p = projectRepository.findById(projectId).orElseThrow();
        return new ProjectDetailDto(
            p.getClient().getClientName(),
            p.getClient().getClientAddress(),
            p.getClient().getClientPhone(),
            p.getProjectTitle(),
            p.getProjectInfo(),
            p.getProjectBudget(),
            p.getProjectPeriod()
        );
    }
    
}
