package org.example.onlinegameapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.RegisterRequest;
import org.example.onlinegameapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody @Valid RegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }
}
