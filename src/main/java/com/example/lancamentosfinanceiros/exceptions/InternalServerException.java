package com.example.lancamentosfinanceiros.exceptions;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
