package org.example.onlinegameapi.repository;

import org.example.onlinegameapi.entity.Card;
import org.example.onlinegameapi.enums.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    List<Card> findAllByRarity(Rarity rarity);
}
