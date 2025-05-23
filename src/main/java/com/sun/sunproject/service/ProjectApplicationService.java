package com.sun.sunproject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.sunproject.dto.MyApplicationDto;
import com.sun.sunproject.entity.FreelancerEntity;
import com.sun.sunproject.entity.ProjectApplicationEntity;
import com.sun.sunproject.entity.ProjectEntity;
import com.sun.sunproject.entity.UserEntity;
import com.sun.sunproject.repository.FreelancerRepository;
import com.sun.sunproject.repository.ProjectApplicationRepository;
import com.sun.sunproject.repository.ProjectRepository;

@Service
public class ProjectApplicationService {
    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private ProjectApplicationRepository projectApplicationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public void saveApplicationForFreelancer(UserEntity user, Long projectIdx) {
        FreelancerEntity freelancer = freelancerRepository.findByUser(user);

        if (freelancer == null) {
            throw new IllegalStateException("프리랜서 정보를 찾을 수 없습니다.");
        }

        ProjectEntity project = projectRepository.findById(projectIdx)
            .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트를 찾을 수 없습니다."));

        ProjectApplicationEntity application = new ProjectApplicationEntity();
        application.setFreelancer(freelancer);
        application.setProject(project);
        application.setTime(LocalDateTime.now());

        projectApplicationRepository.save(application);
    }

    // 마이페이지 프로젝트 조회
    public List<MyApplicationDto> getApplicationsByUser(UserEntity user) {
        FreelancerEntity freelancer = freelancerRepository.findByUser_UserIdx(user.getUserIdx());
        if (freelancer == null) {
            throw new IllegalStateException("프리랜서 정보 없음: " + user.getUserId());
        }

        List<ProjectApplicationEntity> applications = projectApplicationRepository.findByFreelancer(freelancer);

        return applications.stream()
            .map(application -> new MyApplicationDto(
                application.getProject().getProjectIdx(),
                application.getProject().getProjectTitle(),
                application.getTime()
            ))
            .collect(Collectors.toList());
    }
}