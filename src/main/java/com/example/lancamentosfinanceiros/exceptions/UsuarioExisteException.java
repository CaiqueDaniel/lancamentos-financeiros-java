package com.example.lancamentosfinanceiros.exceptions;

public class UsuarioExisteException extends RuntimeException{
    public UsuarioExisteException(){
        super("Usuário já existe.");
    }
}
