package com.sun.sunproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sun.sunproject.dto.ClientDto;
import com.sun.sunproject.dto.MyProjectDto;
import com.sun.sunproject.service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/api/clients")
    public List<ClientDto> getClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/api/clients/{userId}/projects")
    public List<MyProjectDto> getClientProjects(@PathVariable String userId) {
        return clientService.getRegisteredProjects(userId);
    }
}
