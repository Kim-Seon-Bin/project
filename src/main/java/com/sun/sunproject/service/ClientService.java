package com.sun.sunproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sun.sunproject.dto.ClientDto;
import com.sun.sunproject.dto.MyProjectDto;
import com.sun.sunproject.entity.ClientEntity;
import com.sun.sunproject.entity.ProjectEntity;
import com.sun.sunproject.repository.ClientRepository;
import com.sun.sunproject.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(client -> 
            new ClientDto(
                client.getClientName(),
                client.getClientAddress(),
                client.getClientPhone()
            )
        ).collect(Collectors.toList());
    }

    public List<MyProjectDto> getRegisteredProjects(String userId) {
        ClientEntity client = clientRepository.findByUserId(userId);

        if (client == null) {
            throw new IllegalArgumentException("클라이언트 정보가 존재하지 않습니다.");
        }

        List<ProjectEntity> projects = projectRepository.findByClient(client);

        return projects.stream()
            .map(project -> new MyProjectDto(
                project.getProjectTitle(),
                project.getTime()
            ))
            .collect(Collectors.toList());
    }
}
