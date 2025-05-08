package com.sun.sunproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.sunproject.dto.FreelancerDto;
import com.sun.sunproject.service.FreelancerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FreelancerController {
    private final FreelancerService freelancerService;

    @GetMapping("/api/freelancers")
    public List<FreelancerDto> getFreelancers() {
        return freelancerService.getAllFreelancers();
    }
}
