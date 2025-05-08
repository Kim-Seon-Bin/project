package com.sun.sunproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sun.sunproject.dto.ProjectDetailDto;
import com.sun.sunproject.dto.ProjectDto;
import com.sun.sunproject.entity.ClientEntity;
import com.sun.sunproject.entity.ProjectEntity;
import com.sun.sunproject.entity.ProjectSkillEntity;
import com.sun.sunproject.entity.SkillEntity;
import com.sun.sunproject.repository.ClientRepository;
import com.sun.sunproject.repository.ProjectRepository;
import com.sun.sunproject.repository.ProjectSkillRepository;
import com.sun.sunproject.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;
    private final SkillRepository skillRepository;
    private final ProjectSkillRepository projectSkillRepository;

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
       
        List<String> skills = p.getProjectSkills().stream()
        .map(ps -> ps.getSkill().getSkillName())
        .collect(Collectors.toList());

        return new ProjectDetailDto(
            p.getClient().getClientName(),
            p.getClient().getClientAddress(),
            p.getClient().getClientPhone(),
            p.getProjectTitle(),
            p.getProjectInfo(),
            p.getProjectBudget(),
            p.getProjectPeriod(),
            skills
        );
    }
    
    public void registerProject(ProjectDetailDto dto) {
        // 1. 클라이언트 저장 또는 조회
        ClientEntity client = new ClientEntity();
        client.setClientName(dto.getClientName());
        client.setClientAddress(dto.getClientAddress());
        client.setClientPhone(dto.getClientPhone());
        client = clientRepository.save(client);

        // 2. 프로젝트 저장
        ProjectEntity project = new ProjectEntity();
        project.setClient(client);
        project.setProjectTitle(dto.getProjectTitle());
        project.setProjectInfo(dto.getProjectInfo());
        project.setProjectBudget(dto.getProjectBudget());
        project.setProjectPeriod(dto.getProjectPeriod());
        project = projectRepository.save(project);

        // 3. 스킬 매핑
        for (String skillName : dto.getRequiredSkills()) {
            SkillEntity skill = skillRepository.findBySkillName(skillName)
                .orElseGet(() -> skillRepository.save(new SkillEntity(skillName)));
        
            ProjectSkillEntity projectSkill = new ProjectSkillEntity();
            projectSkill.setProject(project);
            projectSkill.setSkill(skill);
            
            projectSkillRepository.save(projectSkill); 
        }
    }
}
