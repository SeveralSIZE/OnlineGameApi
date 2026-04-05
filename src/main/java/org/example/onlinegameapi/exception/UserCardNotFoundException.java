package org.example.onlinegameapi.exception;

public class UserCardNotFoundException extends RuntimeException {
    public UserCardNotFoundException(String message) {
        super(message);
    }
}
