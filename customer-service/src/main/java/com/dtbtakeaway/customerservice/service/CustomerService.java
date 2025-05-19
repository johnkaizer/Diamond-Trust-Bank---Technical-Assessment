package com.dtbtakeaway.customerservice.service;

import com.dtbtakeaway.customerservice.dto.CustomerDTO;
import com.dtbtakeaway.customerservice.dto.CustomerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerDTO dto);

    CustomerResponseDTO updateCustomer(UUID id, CustomerDTO dto);

    void deleteCustomer(UUID id);

    Page<CustomerResponseDTO> getCustomers(String name, LocalDate from, LocalDate to, Pageable pageable);

    CustomerResponseDTO getCustomerById(UUID id);
}

