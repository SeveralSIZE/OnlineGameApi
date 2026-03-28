package org.example.onlinegameapi.service;

import org.example.onlinegameapi.dto.request.LoginRequest;
import org.example.onlinegameapi.dto.request.RefreshRequest;
import org.example.onlinegameapi.dto.request.RegisterRequest;
import org.example.onlinegameapi.dto.response.TokensResponse;

public interface AuthService {
    TokensResponse register(RegisterRequest request);
    TokensResponse login(LoginRequest request);
    TokensResponse refresh(RefreshRequest request);
    void logout(RefreshRequest request);
}
