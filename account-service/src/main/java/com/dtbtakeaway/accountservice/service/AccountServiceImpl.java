package com.dtbtakeaway.accountservice.service;

import com.dtbtakeaway.accountservice.dto.AccountDTO;
import com.dtbtakeaway.accountservice.dto.AccountResponseDTO;
import com.dtbtakeaway.accountservice.entity.Account;
import com.dtbtakeaway.accountservice.exception.ResourceNotFoundException;
import com.dtbtakeaway.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;

    @Override
    public AccountResponseDTO create(AccountDTO dto) {
        Account acc = Account.builder()
                .id(UUID.randomUUID())
                .iban(dto.getIban())
                .bicSwift(dto.getBicSwift())
                .customerId(dto.getCustomerId())
                .build();
        return toResponse(repo.save(acc));
    }

    @Override
    public AccountResponseDTO update(UUID id, AccountDTO dto) {
        Account acc = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        acc.setIban(dto.getIban());
        acc.setBicSwift(dto.getBicSwift());
        acc.setCustomerId(dto.getCustomerId());
        return toResponse(repo.save(acc));
    }

    @Override
    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Account not found");
        repo.deleteById(id);
    }

    @Override
    public Page<AccountResponseDTO> getAll(String iban, String bicSwift, Pageable pageable) {
        return repo.findAll((root, query, cb) -> {
            var predicates = cb.conjunction();
            if (iban != null && !iban.isEmpty())
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("iban")), "%" + iban.toLowerCase() + "%"));
            if (bicSwift != null && !bicSwift.isEmpty())
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("bicSwift")), "%" + bicSwift.toLowerCase() + "%"));
            return predicates;
        }, pageable).map(this::toResponse);
    }

    @Override
    public AccountResponseDTO getById(UUID id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    private AccountResponseDTO toResponse(Account acc) {
        return AccountResponseDTO.builder()
                .id(acc.getId())
                .iban(acc.getIban())
                .bicSwift(acc.getBicSwift())
                .customerId(acc.getCustomerId())
                .build();
    }
}
