package com.sun.sunproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sun.sunproject.dto.FreelancerDto;
import com.sun.sunproject.entity.FreelancerEntity;
import com.sun.sunproject.entity.FreelancerSkillEntity;
import com.sun.sunproject.entity.SkillEntity;
import com.sun.sunproject.entity.UserEntity;
import com.sun.sunproject.repository.FreelancerRepository;
import com.sun.sunproject.repository.FreelancerSkillRepository;
import com.sun.sunproject.repository.SkillRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreelancerService {
    private final FreelancerRepository freelancerRepository;
    private final SkillRepository skillRepository;
       private final FreelancerSkillRepository freelancerSkillRepository;

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

    @Transactional
    public void createFreelancer(FreelancerDto dto, UserEntity user) {
        // 프리랜서 엔티티 생성
        FreelancerEntity freelancer = new FreelancerEntity();
        freelancer.setFreelancerName(dto.getFreelancerName());
        freelancer.setFreelancerIntro(dto.getFreelancerIntro());
        freelancer.setFreelancerPortfolio(dto.getFreelancerPortfolio());
        
        freelancer.setUser(user);

        // 저장 (ID 필요)
        freelancerRepository.save(freelancer);

        // 프리랜서 스킬 생성 및 저장
        List<FreelancerSkillEntity> freelancerSkills = dto.getFreelancerSkills().stream()
        .map(skillName -> {
            if (skillName == null || skillName.trim().isEmpty()) return null;

            SkillEntity skill = skillRepository.findBySkillName(skillName.trim())
                .orElseGet(() -> {
                    SkillEntity newSkill = new SkillEntity();
                    newSkill.setSkillName(skillName.trim());
                    return skillRepository.save(newSkill);
                });

            FreelancerSkillEntity fSkill = new FreelancerSkillEntity();
            fSkill.setFreelancer(freelancer);
            fSkill.setSkill(skill);
            return fSkill;
        })
        .filter(f -> f != null)
        .toList();

        freelancerSkillRepository.saveAll(freelancerSkills);
    }
}
