package com.example.lancamentosfinanceiros.controllers.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RequestLancamentoDto {
    public BigDecimal valor;
    public String descricao;
    public LocalDate data;
}
