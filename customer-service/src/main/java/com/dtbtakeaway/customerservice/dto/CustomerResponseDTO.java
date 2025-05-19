package com.dtbtakeaway.customerservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String otherName;
    private LocalDateTime createdAt;
}

