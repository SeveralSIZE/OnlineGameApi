package org.example.onlinegameapi.service;

import org.example.onlinegameapi.dto.response.UserCardResponse;
import org.example.onlinegameapi.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse getMe(String id);
    List<UserCardResponse> getMyInventory(UUID id);
}
