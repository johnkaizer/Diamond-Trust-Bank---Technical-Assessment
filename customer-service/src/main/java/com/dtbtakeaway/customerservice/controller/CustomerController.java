package com.dtbtakeaway.customerservice.controller;

import com.dtbtakeaway.customerservice.dto.CustomerDTO;
import com.dtbtakeaway.customerservice.dto.CustomerResponseDTO;
import com.dtbtakeaway.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public CustomerResponseDTO create(@Valid @RequestBody CustomerDTO dto) {
        return service.createCustomer(dto);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO update(@PathVariable UUID id, @Valid @RequestBody CustomerDTO dto) {
        return service.updateCustomer(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteCustomer(id);
    }

    @GetMapping
    public Page<CustomerResponseDTO> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getCustomers(name, from, to, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getById(@PathVariable UUID id) {
        return service.getCustomerById(id);
    }
}
