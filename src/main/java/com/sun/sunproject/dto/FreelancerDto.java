package com.sun.sunproject.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreelancerDto {
    private String freelancerName;
    private String freelancerIntro;
    private String freelancerPortfolio;
    private List<String> freelancerSkills;
}