package com.example.lancamentosfinanceiros.exceptions;

public class UserConflictException extends RuntimeException{
    public UserConflictException(){
        super("Usuário já existe.");
    }
}
