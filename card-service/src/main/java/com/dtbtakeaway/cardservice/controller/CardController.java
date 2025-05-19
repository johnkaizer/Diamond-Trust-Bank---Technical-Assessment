package com.dtbtakeaway.cardservice.controller;

import com.dtbtakeaway.cardservice.dto.CardDTO;
import com.dtbtakeaway.cardservice.dto.CardResponseDTO;
import com.dtbtakeaway.cardservice.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService service;

    @PostMapping
    public CardResponseDTO create(@Valid @RequestBody CardDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CardResponseDTO update(@PathVariable UUID id, @Valid @RequestBody CardDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public CardResponseDTO getById(@PathVariable UUID id,
                                   @RequestParam(defaultValue = "false") boolean showFull) {
        return service.getById(id, showFull);
    }

    @GetMapping
    public Page<CardResponseDTO> getAll(
            @RequestParam(required = false) String alias,
            @RequestParam(required = false) String pan,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "false") boolean showFull,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getAll(alias, pan, type, showFull, PageRequest.of(page, size));
    }
}
