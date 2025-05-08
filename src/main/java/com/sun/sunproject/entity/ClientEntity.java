package com.sun.sunproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="TB_CLIENT")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long clientIdx;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_IDX")
    private UserEntity user;

    @Column(name="CLIENT_NAME")
    private String clientName;

    @Column(name="CLIENT_ADDRESS")
    private String clientAddress;

    @Column(name="CLIENT_PHONE")
    private String clientPhone;
}
