package org.example.onlinegameapi.service;

import org.example.onlinegameapi.dto.request.ClickRequest;
import org.example.onlinegameapi.dto.response.ClickResponse;

import java.util.UUID;

public interface GameService {
    ClickResponse click(UUID userId, ClickRequest request);
}
