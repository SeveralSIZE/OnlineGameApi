package org.example.onlinegameapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.CreateCardRequest;
import org.example.onlinegameapi.dto.response.CardResponse;
import org.example.onlinegameapi.entity.Card;
import org.example.onlinegameapi.exception.CardNotFoundException;
import org.example.onlinegameapi.mapper.CardMapper;
import org.example.onlinegameapi.repository.CardRepository;
import org.example.onlinegameapi.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardMapper mapper;
    private final CardRepository repository;

    @Override
    public UUID create(CreateCardRequest request){
        Card card = mapper.toEntity(request);

        repository.save(card);

        return card.getId();
    }

    @Override
    public CardResponse getById(UUID id){
        Card card = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));

        return mapper.toDto(card);
    }

    @Override
    public List<CardResponse> getAll(){
        return repository.findAll()
                .stream()
                .map((c -> mapper.toDto(c)))
                .toList();
    }
}
