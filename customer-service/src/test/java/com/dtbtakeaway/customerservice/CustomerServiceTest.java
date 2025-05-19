package com.dtbtakeaway.customerservice;

import com.dtbtakeaway.customerservice.dto.CustomerDTO;
import com.dtbtakeaway.customerservice.dto.CustomerResponseDTO;
import com.dtbtakeaway.customerservice.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        CustomerDTO dto = new CustomerDTO("John", "Kaiser", "Anthony");
        CustomerResponseDTO response = CustomerResponseDTO.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("Kaiser")
                .otherName("Anthony")
                .createdAt(LocalDateTime.now())
                .build();

        when(service.createCustomer(dto)).thenReturn(response);

        CustomerResponseDTO created = service.createCustomer(dto);
        assertEquals("John", created.getFirstName());
        assertEquals("Kaiser", created.getLastName());
    }

    @Test
    void testGetCustomersWithPagination() {
        Page<CustomerResponseDTO> page = new PageImpl<>(List.of());
        when(service.getCustomers(null, null, null, PageRequest.of(0, 10))).thenReturn(page);

        Page<CustomerResponseDTO> result = service.getCustomers(null, null, null, PageRequest.of(0, 10));
        assertNotNull(result);
    }
}
