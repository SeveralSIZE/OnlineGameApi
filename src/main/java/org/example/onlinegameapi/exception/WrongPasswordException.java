package org.example.onlinegameapi.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Неверный пароль");
    }
}
