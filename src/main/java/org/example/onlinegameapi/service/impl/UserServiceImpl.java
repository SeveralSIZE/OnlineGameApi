package org.example.onlinegameapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.RegisterRequest;
import org.example.onlinegameapi.dto.response.CardResponse;
import org.example.onlinegameapi.dto.response.UserCardResponse;
import org.example.onlinegameapi.dto.response.UserResponse;
import org.example.onlinegameapi.entity.Card;
import org.example.onlinegameapi.entity.User;
import org.example.onlinegameapi.entity.UserCard;
import org.example.onlinegameapi.exception.UserNotFoundException;
import org.example.onlinegameapi.mapper.CardMapper;
import org.example.onlinegameapi.mapper.UserMapper;
import org.example.onlinegameapi.repository.CardRepository;
import org.example.onlinegameapi.repository.UserCardRepository;
import org.example.onlinegameapi.repository.UserRepository;
import org.example.onlinegameapi.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CardMapper cardMapper;
    private final UserCardRepository userCardRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserResponse getMe(UUID id){
        String sql = "SELECT id, username, email FROM users WHERE id = '" + id + "'";

        User user2 = jdbcTemplate.queryForObject(sql, (rs, rn) -> new User(), id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toDto(user);
    }

    @Override
    public List<UserCardResponse> getMyInventory(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userCardRepository.findAllByUserId(user.getId())
                .stream()
                .map(userCard -> UserCardResponse.builder()
                        .id(userCard.getId())
                        .card(cardMapper.toDto(userCard.getCard()))
                        .amount(userCard.getAmount())
                        .build())
                .toList();
    }
}
