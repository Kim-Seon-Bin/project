package com.sun.sunproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sun.sunproject.dto.FreelancerDto;
import com.sun.sunproject.repository.FreelancerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreelancerService {
    private final FreelancerRepository freelancerRepository;

    public List<FreelancerDto> getAllFreelancers() {
        return freelancerRepository.findAll().stream().map(f -> {
            List<String> skills = f.getFreelancerSkills().stream().map(fs -> fs.getSkill().getSkillName()).collect(Collectors.toList());
            return new FreelancerDto(
                f.getFreelancerName(),
                f.getFreelancerIntro(),
                f.getFreelancerPortfolio(),
                skills
            );
        }).collect(Collectors.toList());
    }
}
