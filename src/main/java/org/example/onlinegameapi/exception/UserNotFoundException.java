package org.example.onlinegameapi.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("Пользователь с email: " + email + " не найден");
    }
    public UserNotFoundException(UUID id) {
        super("Пользователь с id: " + id + " не найден");
    }
}
