package com.sun.sunproject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sun.sunproject.dto.FreelancerDto;
import com.sun.sunproject.dto.ProjectDetailDto;
import com.sun.sunproject.dto.ProjectDto;
import com.sun.sunproject.entity.ClientEntity;
import com.sun.sunproject.entity.FreelancerEntity;
import com.sun.sunproject.entity.ProjectApplicationEntity;
import com.sun.sunproject.entity.ProjectEntity;
import com.sun.sunproject.entity.ProjectSkillEntity;
import com.sun.sunproject.entity.SkillEntity;
import com.sun.sunproject.entity.UserEntity;
import com.sun.sunproject.repository.ClientRepository;
import com.sun.sunproject.repository.ProjectApplicationRepository;
import com.sun.sunproject.repository.ProjectRepository;
import com.sun.sunproject.repository.ProjectSkillRepository;
import com.sun.sunproject.repository.SkillRepository;
import com.sun.sunproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;
    private final SkillRepository skillRepository;
    private final ProjectSkillRepository projectSkillRepository;
    private final UserRepository userRepository;
    private final ProjectApplicationRepository projectApplicationRepository;

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream().map(p -> {
            List<String> skills = p.getProjectSkills().stream()
                .map(ps -> ps.getSkill().getSkillName())
                .collect(Collectors.toList());

            return new ProjectDto(
                p.getProjectIdx(),
                p.getClient().getClientName(),
                p.getProjectTitle(),
                skills,
                p.getTime()
            );
        }).collect(Collectors.toList());
    }

    public ProjectDetailDto getProjectDetail(Long projectId) {
        ProjectEntity p = projectRepository.findById(projectId).orElseThrow();
       
        List<String> skills = p.getProjectSkills().stream()
        .map(ps -> ps.getSkill().getSkillName())
        .collect(Collectors.toList());

        return new ProjectDetailDto(
            p.getProjectIdx(),
            p.getClient().getClientName(),
            p.getClient().getClientAddress(),
            p.getClient().getClientPhone(),
            p.getProjectTitle(),
            p.getProjectInfo(),
            p.getProjectBudget(),
            p.getProjectPeriod(),
            skills,
            p.getTime()
        );
    }
    
    public void registerProject(String userId, ProjectDetailDto dto) {
        ClientEntity client = clientRepository.findByUserId(userId);
        
        // 1. 클라이언트 저장 또는 조회
        if (client == null) {
            UserEntity user = userRepository.findByUserId(userId);
            client = new ClientEntity();
            client.setUser(user);
            client.setClientName(dto.getClientName());
            client.setClientAddress(dto.getClientAddress());
            client.setClientPhone(dto.getClientPhone());
            client = clientRepository.save(client);
        }

        // 2. 프로젝트 저장
        ProjectEntity project = new ProjectEntity();
        project.setClient(client);
        project.setProjectTitle(dto.getProjectTitle());
        project.setProjectInfo(dto.getProjectInfo());
        project.setProjectBudget(dto.getProjectBudget());
        project.setProjectPeriod(dto.getProjectPeriod());
        project.setTime(LocalDateTime.now());
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

    public List<ProjectDto> getProjectsByUserId(String userId) {
        ClientEntity client = clientRepository.findByUserId(userId);
        if (client == null) {
            return List.of();  
        }

        return client.getProjects().stream().map(p -> {
            List<String> skills = p.getProjectSkills().stream()
                .map(ps -> ps.getSkill().getSkillName())
                .collect(Collectors.toList());

            return new ProjectDto(
                p.getProjectIdx(),
                p.getClient().getClientName(),
                p.getProjectTitle(),
                skills,
                p.getTime()
            );
        }).collect(Collectors.toList());
    }

    public List<FreelancerDto> getApplicantsByProject(Long projectId) {
        ProjectEntity project = projectRepository.findById(projectId)
            .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 없습니다."));

        List<ProjectApplicationEntity> applications = projectApplicationRepository.findByProject(project);

        return applications.stream()
            .map(app -> {
                FreelancerEntity freelancer = app.getFreelancer();
                FreelancerDto dto = new FreelancerDto();
                dto.setFreelancerName(freelancer.getFreelancerName());
                dto.setFreelancerIntro(freelancer.getFreelancerIntro());
                dto.setFreelancerPortfolio(freelancer.getFreelancerPortfolio());
                // 필요하다면 스킬 리스트도 셋팅
                // 예) dto.setFreelancerSkills(...);
                return dto;
            })
            .collect(Collectors.toList());
    }
}
