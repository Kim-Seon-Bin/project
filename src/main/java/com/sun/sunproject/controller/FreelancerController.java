package com.sun.sunproject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/api/freelancers")
    public ResponseEntity<Void> createFreelancer(@RequestBody FreelancerDto dto) {
        freelancerService.createFreelancer(dto);
        return ResponseEntity.ok().build();
    }
}
