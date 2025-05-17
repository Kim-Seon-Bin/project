package com.sun.sunproject.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyProjectDto {
    private Long projectIdx;
    private String projectTitle;
    private LocalDateTime createdDate;
}
