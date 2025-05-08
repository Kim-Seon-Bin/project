package com.sun.sunproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDto {
    private String clientName;
    private String clientAddress;
    private String clientPhone;
}
