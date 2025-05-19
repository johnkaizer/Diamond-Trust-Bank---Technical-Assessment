package com.dtbtakeaway.accountservice.controller;

import com.dtbtakeaway.accountservice.dto.AccountDTO;
import com.dtbtakeaway.accountservice.dto.AccountResponseDTO;
import com.dtbtakeaway.accountservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping
    public AccountResponseDTO create(@Valid @RequestBody AccountDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public AccountResponseDTO update(@PathVariable UUID id, @Valid @RequestBody AccountDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping
    public Page<AccountResponseDTO> getAll(
            @RequestParam(required = false) String iban,
            @RequestParam(required = false) String bicSwift,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getAll(iban, bicSwift, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getById(@PathVariable UUID id) {
        return service.getById(id);
    }
}

