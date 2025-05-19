package com.dtbtakeaway.cardservice.dto;

import com.dtbtakeaway.cardservice.entity.CardType;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponseDTO {
    private UUID id;
    private String cardAlias;
    private UUID accountId;
    private CardType type;
    private String pan;
    private String cvv;
}

