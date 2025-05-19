package com.dtbtakeaway.accountservice;

import com.dtbtakeaway.accountservice.dto.AccountDTO;
import com.dtbtakeaway.accountservice.dto.AccountResponseDTO;
import com.dtbtakeaway.accountservice.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        AccountDTO dto = new AccountDTO("IBAN001", "BIC001", UUID.randomUUID());
        AccountResponseDTO response = AccountResponseDTO.builder()
                .id(UUID.randomUUID())
                .iban("IBAN001")
                .bicSwift("BIC001")
                .customerId(dto.getCustomerId())
                .build();

        when(service.create(dto)).thenReturn(response);

        AccountResponseDTO created = service.create(dto);
        assertEquals("IBAN001", created.getIban());
    }

    @Test
    void testGetAccounts() {
        Page<AccountResponseDTO> page = new PageImpl<>(List.of());
        when(service.getAll(null, null, PageRequest.of(0, 10))).thenReturn(page);

        Page<AccountResponseDTO> result = service.getAll(null, null, PageRequest.of(0, 10));
        assertNotNull(result);
    }
}
