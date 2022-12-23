package com.example.lancamentosfinanceiros.controllers.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RequestFinancialReleaseDto {
    public BigDecimal valor;
    public String descricao;
    public LocalDate data;
}
