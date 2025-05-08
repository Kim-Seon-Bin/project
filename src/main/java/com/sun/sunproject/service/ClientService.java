package com.sun.sunproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.sun.sunproject.dto.ClientDto;
import com.sun.sunproject.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(client -> 
            new ClientDto(
                client.getClientName(),
                client.getClientAddress(),
                client.getClientPhone()
            )
        ).collect(Collectors.toList());
    }
}
