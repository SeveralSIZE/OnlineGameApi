package org.example.onlinegameapi.service;

import org.example.onlinegameapi.dto.request.CreateCardRequest;
import org.example.onlinegameapi.dto.response.CardResponse;

import java.util.List;
import java.util.UUID;

public interface CardService {
    UUID create(CreateCardRequest request);
    CardResponse getById(UUID id);
    List<CardResponse> getAll();
}
