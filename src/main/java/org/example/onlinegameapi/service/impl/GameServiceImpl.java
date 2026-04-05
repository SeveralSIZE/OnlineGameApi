package org.example.onlinegameapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.ClickRequest;
import org.example.onlinegameapi.dto.response.ClickResponse;
import org.example.onlinegameapi.dto.response.SellResponse;
import org.example.onlinegameapi.entity.User;
import org.example.onlinegameapi.entity.UserCard;
import org.example.onlinegameapi.enums.Rarity;
import org.example.onlinegameapi.exception.UserCardNotFoundException;
import org.example.onlinegameapi.exception.UserNotFoundException;
import org.example.onlinegameapi.repository.UserCardRepository;
import org.example.onlinegameapi.repository.UserRepository;
import org.example.onlinegameapi.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final UserRepository userRepository;
    private final UserCardRepository userCardRepository;
    private final Random random = new Random();

    @Override
    public ClickResponse click(UUID userId, ClickRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setCoins(user.getCoins() + request.getClicks());

        userRepository.save(user);
        return ClickResponse.builder()
                .coins(user.getCoins())
                .build();
    }

    @Override
    public SellResponse sellCard(UUID userId, UUID userCardId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        UserCard userCard = userCardRepository.findById(userCardId)
                .orElseThrow(() -> new UserCardNotFoundException("Связь не найдена"));

        if(userCard.getAmount() > 1){
            userCard.setAmount(userCard.getAmount() - 1);
            userCardRepository.save(userCard);
        }
        else{
            userCardRepository.delete(userCard);
        }

        int sellPrice = getSellPrice(userCard.getCard().getRarity());
        user.setCoins(user.getCoins() + sellPrice);

        userRepository.save(user);

        return SellResponse.builder()
                .coins(user.getCoins())
                .sellPrice(sellPrice)
                .build();
    }

    private int getSellPrice(Rarity rarity) {
        int basePrice = switch (rarity) {
            case COMMON -> 10;
            case UNCOMMON -> 40;
            case RARE -> 125;
            case EPIC -> 500;
            case LEGENDARY -> 2500;
            case ETERNAL -> 12000;
        };

        float multiplier = 0.9f + random.nextFloat() * 0.2f;
        return Math.round(basePrice * multiplier);
    }
}
