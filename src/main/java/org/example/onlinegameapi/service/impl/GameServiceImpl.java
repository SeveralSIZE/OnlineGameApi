package org.example.onlinegameapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.ClickRequest;
import org.example.onlinegameapi.dto.response.ClickResponse;
import org.example.onlinegameapi.entity.User;
import org.example.onlinegameapi.exception.UserNotFoundException;
import org.example.onlinegameapi.repository.UserRepository;
import org.example.onlinegameapi.service.GameService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final UserRepository userRepository;

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
}
