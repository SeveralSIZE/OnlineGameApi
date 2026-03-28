package org.example.onlinegameapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.LoginRequest;
import org.example.onlinegameapi.dto.request.RefreshRequest;
import org.example.onlinegameapi.dto.request.RegisterRequest;
import org.example.onlinegameapi.dto.response.TokensResponse;
import org.example.onlinegameapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity<TokensResponse> login(@RequestBody @Valid LoginRequest request){

    }

    @PostMapping("/register")
    public ResponseEntity<TokensResponse> register(@RequestBody @Valid RegisterRequest request){

    }

    @PostMapping("/refresh")
    public ResponseEntity<TokensResponse> refresh(@RequestBody @Valid RefreshRequest request){

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){

    }
}
