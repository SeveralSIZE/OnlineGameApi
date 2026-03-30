package org.example.onlinegameapi.exception;

public class NotEnoughCoinsException extends RuntimeException {
    public NotEnoughCoinsException() {
        super("Недостаточно денег");
    }
}
