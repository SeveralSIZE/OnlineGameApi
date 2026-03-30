package org.example.onlinegameapi.exception;

import org.example.onlinegameapi.enums.Rarity;

import java.util.UUID;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(UUID id) {
        super("Карточка с id: " + id + " не найдена");
    }
    public CardNotFoundException(Rarity rarity){
        super("Нет карточек с редкостью: " + rarity);
    }
}
