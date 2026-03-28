package org.example.onlinegameapi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("Пользователь с email: " + email + " не найден");
    }
}
