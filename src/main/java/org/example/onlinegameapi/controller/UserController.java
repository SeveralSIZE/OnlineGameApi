package org.example.onlinegameapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.response.UserCardResponse;
import org.example.onlinegameapi.dto.response.UserResponse;
import org.example.onlinegameapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(Authentication authentication){
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(service.getMe(userId));
    }

    @GetMapping("/me/inventory")
    public ResponseEntity<List<UserCardResponse>> getMyInventory(Authentication authentication){
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(service.getMyInventory(userId));
    }
}
