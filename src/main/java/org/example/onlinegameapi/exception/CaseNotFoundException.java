package org.example.onlinegameapi.exception;

import java.util.UUID;

public class CaseNotFoundException extends RuntimeException {
    public CaseNotFoundException(UUID id) {
        super("Кейс с id: " + id + " не найден");
    }
}
