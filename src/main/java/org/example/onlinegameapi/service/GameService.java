package org.example.onlinegameapi.service;

import org.example.onlinegameapi.dto.request.ClickRequest;
import org.example.onlinegameapi.dto.response.SellResponse;
import org.example.onlinegameapi.dto.response.ClickResponse;
import org.example.onlinegameapi.entity.UserCard;

import java.util.UUID;

public interface GameService {
    ClickResponse click(UUID userId, ClickRequest request);
    SellResponse sellCard(UUID userId, UUID userCardId);
}
