package com.dtbtakeaway.cardservice.repository;

import com.dtbtakeaway.cardservice.entity.Card;
import com.dtbtakeaway.cardservice.entity.CardType;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID>, JpaSpecificationExecutor<Card> {
    long countByAccountId(UUID accountId);
    boolean existsByAccountIdAndType(UUID accountId, CardType type);
}

