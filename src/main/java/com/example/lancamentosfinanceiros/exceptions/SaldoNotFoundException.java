package com.example.lancamentosfinanceiros.exceptions;

public class SaldoNotFoundException extends RuntimeException {
    public SaldoNotFoundException() {
        super("Saldo não encontrado.");
    }
}
