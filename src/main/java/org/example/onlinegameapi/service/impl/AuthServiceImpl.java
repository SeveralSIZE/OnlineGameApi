package org.example.onlinegameapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.LoginRequest;
import org.example.onlinegameapi.dto.request.RefreshRequest;
import org.example.onlinegameapi.dto.request.RegisterRequest;
import org.example.onlinegameapi.dto.response.TokensResponse;
import org.example.onlinegameapi.entity.RefreshToken;
import org.example.onlinegameapi.entity.User;
import org.example.onlinegameapi.exception.UnauthorizedException;
import org.example.onlinegameapi.exception.UserNotFoundException;
import org.example.onlinegameapi.exception.WrongPasswordException;
import org.example.onlinegameapi.mapper.UserMapper;
import org.example.onlinegameapi.repository.RefreshTokenRepository;
import org.example.onlinegameapi.repository.UserRepository;
import org.example.onlinegameapi.service.AuthService;
import org.example.onlinegameapi.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Value("${jwt.access-token-expiration}")
    private long refreshTokenExpiration;

    private String generateRefreshToken(User user){
        String refreshToken = jwtService.generateRefreshToken(user);

        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setUser(user);
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setExpiresAt(OffsetDateTime.now().plus(Duration.ofMillis(refreshTokenExpiration)));
        refreshTokenRepository.save(refreshTokenEntity);

        return refreshToken;
    }

    @Override
    public TokensResponse register(RegisterRequest request){
        User user = userMapper.toEntity(request);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return TokensResponse.builder()
            .accessToken(jwtService.generateAccessToken(user))
            .refreshToken(generateRefreshToken(user))
            .build();
    }

    @Override
    public TokensResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new WrongPasswordException();
        }

        return TokensResponse.builder()
            .accessToken(jwtService.generateAccessToken(user))
            .refreshToken(generateRefreshToken(user))
            .build();
    }

    @Override
    public TokensResponse refresh(RefreshRequest request){
        RefreshToken token = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new UnauthorizedException("Токен не найден"));

        if(token.getExpiresAt().isBefore(OffsetDateTime.now())){
            refreshTokenRepository.delete(token);
            throw new UnauthorizedException("Токен истёк");
        }

        User user = token.getUser();
        refreshTokenRepository.delete(token);

        return TokensResponse.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(generateRefreshToken(user))
                .build();
    }

    @Override
    public void logout(RefreshRequest request){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new UnauthorizedException("Токен не найден"));

        refreshTokenRepository.delete(refreshToken);
    }
}
