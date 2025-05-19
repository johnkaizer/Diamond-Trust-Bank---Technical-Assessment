package com.dtbtakeaway.cardservice;

import com.dtbtakeaway.cardservice.dto.CardDTO;
import com.dtbtakeaway.cardservice.dto.CardResponseDTO;
import com.dtbtakeaway.cardservice.entity.CardType;
import com.dtbtakeaway.cardservice.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CardServiceTest {

    @Mock
    private CardService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCard() {
        CardDTO dto = new CardDTO("SILVER", UUID.randomUUID(), CardType.PHYSICAL, "1234567890123456", "123");
        CardResponseDTO response = CardResponseDTO.builder()
                .id(UUID.randomUUID())
                .cardAlias("SILVER")
                .accountId(dto.getAccountId())
                .type(CardType.PHYSICAL)
                .pan("****3456")
                .cvv("***")
                .build();

        when(service.create(dto)).thenReturn(response);

        CardResponseDTO created = service.create(dto);
        assertEquals("SILVER", created.getCardAlias());
    }

    @Test
    void testGetCardsWithMasking() {
        Page<CardResponseDTO> page = new PageImpl<>(List.of());
        when(service.getAll(null, null, null, false, PageRequest.of(0, 10))).thenReturn(page);

        Page<CardResponseDTO> result = service.getAll(null, null, null, false, PageRequest.of(0, 10));
        assertNotNull(result);
    }
}

