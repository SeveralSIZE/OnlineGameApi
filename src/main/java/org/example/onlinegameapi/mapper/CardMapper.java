package org.example.onlinegameapi.mapper;

import org.example.onlinegameapi.dto.request.CreateCardRequest;
import org.example.onlinegameapi.dto.response.CardResponse;
import org.example.onlinegameapi.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public Card toEntity(CreateCardRequest request){
        if (request == null) return null;

        Card card = new Card();
        card.setDescription(request.getDescription());
        card.setName(request.getName());
        card.setRarity(request.getRarity());
        card.setIconUrl(request.getIconUrl());

        return card;
    }

    public CardResponse toDto(Card card) {
        return CardResponse.builder()
            .id(card.getId())
            .name(card.getName())
            .description(card.getDescription())
            .rarity(card.getRarity())
            .iconUrl(card.getIconUrl())
            .build();
    }
}
