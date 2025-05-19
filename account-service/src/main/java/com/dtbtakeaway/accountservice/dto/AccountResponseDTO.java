package com.dtbtakeaway.accountservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDTO {
    private UUID id;
    private String iban;
    private String bicSwift;
    private UUID customerId;
}

