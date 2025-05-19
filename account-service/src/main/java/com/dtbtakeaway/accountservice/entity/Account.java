package com.dtbtakeaway.accountservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Data
public class Account {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String iban;

    @Column(nullable = false)
    private String bicSwift;

    @Column(nullable = false)
    private UUID customerId;
}

