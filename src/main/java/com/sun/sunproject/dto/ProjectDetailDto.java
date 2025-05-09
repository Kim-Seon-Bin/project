package com.sun.sunproject.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDetailDto {
    private String clientName;
    private String clientAddress;
    private String clientPhone;
    private String projectTitle;
    private String projectInfo;
    private String projectBudget;
    private String projectPeriod;
    private List<String> requiredSkills;
}
