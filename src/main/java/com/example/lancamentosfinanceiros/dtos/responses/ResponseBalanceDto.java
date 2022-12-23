package com.example.lancamentosfinanceiros.dtos.responses;

import com.example.lancamentosfinanceiros.models.Balance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResponseBalanceDto {
    public BigDecimal valor;
    public LocalDateTime ultimaAtualizacao;

    public ResponseBalanceDto(Balance balance) {
        this.valor = balance.getValue();
        this.ultimaAtualizacao = balance.getUpdatedAt();
    }
}
