package com.dtbtakeaway.cardservice.dto;

import com.dtbtakeaway.cardservice.entity.CardType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {

    @NotBlank
    private String cardAlias;

    @NotNull
    private UUID accountId;

    @NotNull
    private CardType type;

    @NotBlank
    private String pan;

    @NotBlank
    @Size(min = 3, max = 3)
    private String cvv;
}
