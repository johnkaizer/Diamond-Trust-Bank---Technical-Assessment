package com.dtbtakeaway.customerservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String otherName;
}

