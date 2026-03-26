package org.example.onlinegameapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.RegisterRequest;
import org.example.onlinegameapi.entity.User;
import org.example.onlinegameapi.mapper.UserMapper;
import org.example.onlinegameapi.repository.UserRepository;
import org.example.onlinegameapi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UUID create(RegisterRequest request){
        User user = mapper.toEntity(request);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        repository.save(user);

        return user.getId();
    }
}
