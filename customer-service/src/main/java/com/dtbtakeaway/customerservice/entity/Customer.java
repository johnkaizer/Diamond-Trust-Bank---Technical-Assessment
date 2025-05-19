package com.dtbtakeaway.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Data
public class Customer {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String otherName;

    private LocalDateTime createdAt;
}

