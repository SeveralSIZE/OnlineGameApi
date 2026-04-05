package org.example.onlinegameapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.ClickRequest;
import org.example.onlinegameapi.dto.response.ClickResponse;
import org.example.onlinegameapi.dto.response.SellResponse;
import org.example.onlinegameapi.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/click")
    public ResponseEntity<ClickResponse> click(Authentication authentication, @RequestBody ClickRequest request){
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(gameService.click(userId, request));
    }

    @PostMapping("/sell/{userCardId}")
    public ResponseEntity<SellResponse> sellCard(Authentication authentication, @PathVariable UUID userCardId){
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(gameService.sellCard(userId, userCardId));
    }
}
