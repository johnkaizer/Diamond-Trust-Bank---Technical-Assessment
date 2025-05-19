package com.dtbtakeaway.cardservice.service;

import com.dtbtakeaway.cardservice.dto.CardDTO;
import com.dtbtakeaway.cardservice.dto.CardResponseDTO;
import com.dtbtakeaway.cardservice.entity.Card;
import com.dtbtakeaway.cardservice.entity.CardType;
import com.dtbtakeaway.cardservice.exception.ResourceNotFoundException;
import com.dtbtakeaway.cardservice.repository.CardRepository;
import com.dtbtakeaway.cardservice.util.CardMaskingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository repo;

    @Override
    public CardResponseDTO create(CardDTO dto) {
        if (repo.existsByAccountIdAndType(dto.getAccountId(), dto.getType())) {
            throw new IllegalArgumentException("Card of this type already exists for the account.");
        }
        if (repo.countByAccountId(dto.getAccountId()) >= 2) {
            throw new IllegalArgumentException("Account already has maximum number of cards.");
        }

        Card card = Card.builder()
                .id(UUID.randomUUID())
                .cardAlias(dto.getCardAlias())
                .accountId(dto.getAccountId())
                .type(dto.getType())
                .pan(dto.getPan())
                .cvv(dto.getCvv())
                .build();
        return toResponse(repo.save(card), false);
    }

    @Override
    public CardResponseDTO update(UUID id, CardDTO dto) {
        Card card = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        card.setCardAlias(dto.getCardAlias());
        return toResponse(repo.save(card), false);
    }

    @Override
    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Card not found");
        repo.deleteById(id);
    }

    @Override
    public CardResponseDTO getById(UUID id, boolean showFull) {
        return repo.findById(id)
                .map(card -> toResponse(card, showFull))
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
    }

    @Override
    public Page<CardResponseDTO> getAll(String alias, String pan, String type, boolean showFull, Pageable pageable) {
        return repo.findAll((root, query, cb) -> {
            var predicates = cb.conjunction();
            if (alias != null && !alias.isEmpty())
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("cardAlias")), "%" + alias.toLowerCase() + "%"));
            if (pan != null && !pan.isEmpty())
                predicates = cb.and(predicates, cb.like(root.get("pan"), "%" + pan + "%"));
            if (type != null)
                predicates = cb.and(predicates, cb.equal(root.get("type"), CardType.valueOf(type.toUpperCase())));
            return predicates;
        }, pageable).map(card -> toResponse(card, showFull));
    }

    private CardResponseDTO toResponse(Card card, boolean showFull) {
        return CardResponseDTO.builder()
                .id(card.getId())
                .cardAlias(card.getCardAlias())
                .accountId(card.getAccountId())
                .type(card.getType())
                .pan(showFull ? card.getPan() : CardMaskingUtil.maskPan(card.getPan()))
                .cvv(showFull ? card.getCvv() : CardMaskingUtil.maskCvv(card.getCvv()))
                .build();
    }
}

