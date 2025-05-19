package com.dtbtakeaway.accountservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {

    @NotBlank
    private String iban;

    @NotBlank
    private String bicSwift;

    @NotNull
    private java.util.UUID customerId;
}

