package com.example.lancamentosfinanceiros.exceptions;

public class ArchiveException extends RuntimeException {
    public ArchiveException(String message, Exception cause) {
        super(message, cause);
    }
}
