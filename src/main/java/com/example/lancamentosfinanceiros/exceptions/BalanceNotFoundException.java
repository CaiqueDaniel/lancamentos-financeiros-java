package com.example.lancamentosfinanceiros.exceptions;

public class BalanceNotFoundException extends RuntimeException {
    public BalanceNotFoundException() {
        super("Saldo não encontrado.");
    }
}
