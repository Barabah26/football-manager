package com.example.footballmanager.exception;

public class InvalidTransferException extends RuntimeException {
    public InvalidTransferException(String message) {
        super(message);
    }
}
