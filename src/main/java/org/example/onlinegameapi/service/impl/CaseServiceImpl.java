package org.example.onlinegameapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.CreateCaseRequest;
import org.example.onlinegameapi.dto.response.CaseResponse;
import org.example.onlinegameapi.dto.response.OpenCaseResponse;
import org.example.onlinegameapi.entity.Card;
import org.example.onlinegameapi.entity.Case;
import org.example.onlinegameapi.entity.User;
import org.example.onlinegameapi.entity.UserCard;
import org.example.onlinegameapi.enums.Rarity;
import org.example.onlinegameapi.exception.CardNotFoundException;
import org.example.onlinegameapi.exception.CaseNotFoundException;
import org.example.onlinegameapi.exception.NotEnoughCoinsException;
import org.example.onlinegameapi.exception.UserNotFoundException;
import org.example.onlinegameapi.mapper.CardMapper;
import org.example.onlinegameapi.mapper.CaseMapper;
import org.example.onlinegameapi.repository.CardRepository;
import org.example.onlinegameapi.repository.CaseRepository;
import org.example.onlinegameapi.repository.UserCardRepository;
import org.example.onlinegameapi.repository.UserRepository;
import org.example.onlinegameapi.service.CaseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CaseServiceImpl implements CaseService {
    private final CaseMapper caseMapper;
    private final CardMapper cardMapper;
    private final CaseRepository caseRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final UserCardRepository userCardRepository;
    private final Random random = new Random();

    @Override
    public UUID create(CreateCaseRequest request){
        Case caseEntity = caseMapper.toEntity(request);

        caseRepository.save(caseEntity);

        return caseEntity.getId();
    }

    @Override
    public CaseResponse getById(UUID id){
        Case caseEntity = caseRepository.findById(id)
                .orElseThrow(() -> new CaseNotFoundException(id));

        return caseMapper.toDto(caseEntity);
    }

    @Override
    public List<CaseResponse> getAll(){
        return caseRepository.findAll()
                .stream()
                .map((c -> caseMapper.toDto(c)))
                .toList();
    }

    @Override
    public OpenCaseResponse openCase(UUID userId, UUID caseId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Case caseEntity = caseRepository.findById(caseId)
                .orElseThrow(() -> new CaseNotFoundException(caseId));

        if(user.getCoins() < caseEntity.getPrice()){
            throw new NotEnoughCoinsException();
        }

        user.setCoins(user.getCoins() - caseEntity.getPrice());

        Rarity rarity = rollRarity(caseEntity);

        List<Card> cards = cardRepository.findAllByRarity(rarity);
        if(cards.isEmpty()) throw new CardNotFoundException(rarity);

        Card card = cards.get(random.nextInt(cards.size()));

        UserCard userCard;
        if(userCardRepository.checkUserCardExisting(userId, card.getId())){
            userCard = userCardRepository.getByCardId(card.getId());
            userCard.setAmount(userCard.getAmount()+1);
        }
        else{
            userCard = new UserCard();
            userCard.setUser(user);
            userCard.setCard(card);
        }
        userCardRepository.save(userCard);

        userRepository.save(user);

        return OpenCaseResponse.builder()
                .card(cardMapper.toDto(card))
                .remainingCoins(user.getCoins())
                .build();
    }

    private Rarity rollRarity(Case caseEntity){
        Map<Rarity, Float> chances = new LinkedHashMap<>();
        chances.put(Rarity.ETERNAL, caseEntity.getEternalChance());
        chances.put(Rarity.LEGENDARY, caseEntity.getLegendaryChance());
        chances.put(Rarity.EPIC, caseEntity.getEpicChance());
        chances.put(Rarity.RARE, caseEntity.getRareChance());
        chances.put(Rarity.UNCOMMON, caseEntity.getUncommonChance());
        chances.put(Rarity.COMMON, caseEntity.getCommonChance());

        float roll = random.nextFloat() * 100;
        float cumulative = 0;
        for(Map.Entry<Rarity, Float> entry : chances.entrySet()){
            cumulative += entry.getValue();
            if(roll < cumulative) return entry.getKey();
        }

        return Rarity.COMMON;
    }
}
