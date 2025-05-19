package com.dtbtakeaway.customerservice.service;

import com.dtbtakeaway.customerservice.dto.CustomerDTO;
import com.dtbtakeaway.customerservice.dto.CustomerResponseDTO;
import com.dtbtakeaway.customerservice.entity.Customer;
import com.dtbtakeaway.customerservice.exception.ResourceNotFoundException;
import com.dtbtakeaway.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    @Override
    public CustomerResponseDTO createCustomer(CustomerDTO dto) {
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .otherName(dto.getOtherName())
                .createdAt(LocalDateTime.now())
                .build();
        return toResponse(repo.save(customer));
    }

    @Override
    public CustomerResponseDTO updateCustomer(UUID id, CustomerDTO dto) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setOtherName(dto.getOtherName());
        return toResponse(repo.save(customer));
    }

    @Override
    public void deleteCustomer(UUID id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Customer not found");
        repo.deleteById(id);
    }

    @Override
    public Page<CustomerResponseDTO> getCustomers(String name, LocalDate from, LocalDate to, Pageable pageable) {
        return repo.findAll((root, query, cb) -> {
            var predicates = cb.conjunction();
            if (name != null && !name.isEmpty()) {
                var pattern = "%" + name.toLowerCase() + "%";
                predicates = cb.and(predicates, cb.or(
                        cb.like(cb.lower(root.get("firstName")), pattern),
                        cb.like(cb.lower(root.get("lastName")), pattern),
                        cb.like(cb.lower(root.get("otherName")), pattern)
                ));
            }
            if (from != null)
                predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("createdAt"), from.atStartOfDay()));
            if (to != null)
                predicates = cb.and(predicates, cb.lessThanOrEqualTo(root.get("createdAt"), to.atTime(23, 59, 59)));
            return predicates;
        }, pageable).map(this::toResponse);
    }

    @Override
    public CustomerResponseDTO getCustomerById(UUID id) {
        return repo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    private CustomerResponseDTO toResponse(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .otherName(customer.getOtherName())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
