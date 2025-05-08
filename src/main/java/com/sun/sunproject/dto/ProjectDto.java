package com.sun.sunproject.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDto {
    private Long projectIdx;
    private String clientName;
    private String projectTitle;
    private List<String> requiredSkills;
}
