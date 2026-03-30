package org.example.onlinegameapi.service;

import org.example.onlinegameapi.dto.request.RegisterRequest;
import org.example.onlinegameapi.dto.response.UserResponse;
import org.example.onlinegameapi.entity.User;

import java.util.UUID;

public interface UserService {
    UserResponse getMe(UUID id);
}
