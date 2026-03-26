package org.example.onlinegameapi.service;

import org.example.onlinegameapi.dto.request.RegisterRequest;

import java.util.UUID;

public interface UserService {
    UUID create(RegisterRequest request);

}
