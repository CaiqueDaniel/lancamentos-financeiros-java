package com.example.lancamentosfinanceiros.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException() {
        super("Usuário não encontrado.");
    }
}
