package org.example.onlinegameapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.onlinegameapi.dto.request.CreateCardRequest;
import org.example.onlinegameapi.dto.request.CreateCaseRequest;
import org.example.onlinegameapi.dto.response.CardResponse;
import org.example.onlinegameapi.dto.response.CaseResponse;
import org.example.onlinegameapi.dto.response.OpenCaseResponse;
import org.example.onlinegameapi.service.CaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/cases")
@RequiredArgsConstructor
public class CaseController {
    private final CaseService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UUID> create(@RequestBody @Valid CreateCaseRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CaseResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/{caseId}/open")
    public ResponseEntity<OpenCaseResponse> openCase(Authentication authentication, @PathVariable UUID caseId){
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(service.openCase(userId, caseId));
    }
}