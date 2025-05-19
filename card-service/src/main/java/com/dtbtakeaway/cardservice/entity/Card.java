package com.dtbtakeaway.cardservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Data
public class Card {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String cardAlias;

    @Column(nullable = false)
    private UUID accountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType type; // PHYSICAL or VIRTUAL

    @Column(nullable = false)
    private String pan;

    @Column(nullable = false)
    private String cvv;
}

