package org.example.onlinegameapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.RegisterRequest;
import org.example.onlinegameapi.dto.response.UserResponse;
import org.example.onlinegameapi.entity.User;
import org.example.onlinegameapi.exception.UserNotFoundException;
import org.example.onlinegameapi.mapper.UserMapper;
import org.example.onlinegameapi.repository.UserRepository;
import org.example.onlinegameapi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserResponse getMe(UUID id){
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return mapper.toDto(user);
    }
}
