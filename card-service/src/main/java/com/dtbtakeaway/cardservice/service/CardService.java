package com.dtbtakeaway.cardservice.service;

import com.dtbtakeaway.cardservice.dto.CardDTO;
import com.dtbtakeaway.cardservice.dto.CardResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CardService {
    CardResponseDTO create(CardDTO dto);
    CardResponseDTO update(UUID id, CardDTO dto);
    void delete(UUID id);
    CardResponseDTO getById(UUID id, boolean showFull);
    Page<CardResponseDTO> getAll(String alias, String pan, String type, boolean showFull, Pageable pageable);
}

