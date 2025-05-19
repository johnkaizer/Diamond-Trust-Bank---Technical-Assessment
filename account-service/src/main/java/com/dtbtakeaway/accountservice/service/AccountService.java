package com.dtbtakeaway.accountservice.service;

import com.dtbtakeaway.accountservice.dto.AccountDTO;
import com.dtbtakeaway.accountservice.dto.AccountResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AccountService {
    AccountResponseDTO create(AccountDTO dto);
    AccountResponseDTO update(UUID id, AccountDTO dto);
    void delete(UUID id);
    Page<AccountResponseDTO> getAll(String iban, String bicSwift, Pageable pageable);
    AccountResponseDTO getById(UUID id);
}
