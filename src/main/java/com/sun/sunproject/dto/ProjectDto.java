package com.sun.sunproject.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long projectIdx;
    private String clientName;
    private String projectTitle;
    private List<String> requiredSkills;
    private LocalDateTime time;
}
