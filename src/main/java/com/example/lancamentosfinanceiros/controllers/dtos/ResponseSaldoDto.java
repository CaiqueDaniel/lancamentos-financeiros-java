package com.example.lancamentosfinanceiros.controllers.dtos;

import com.example.lancamentosfinanceiros.models.Saldo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResponseSaldoDto {
    public BigDecimal valor;
    public LocalDateTime ultimaAtualizacao;

    public ResponseSaldoDto(Saldo saldo) {
        this.valor = saldo.getValor();
        this.ultimaAtualizacao = saldo.getAtualizadoEm();
    }
}
