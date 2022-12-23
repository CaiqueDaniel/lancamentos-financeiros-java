package com.example.lancamentosfinanceiros.controllers.dtos;

import com.example.lancamentosfinanceiros.models.FinancialRelease;
import lombok.Getter;

import java.time.LocalDateTime;

public class ResponseFinancialReleaseDto extends RequestFinancialReleaseDto {
    @Getter
    private final LocalDateTime data;

    public ResponseFinancialReleaseDto(FinancialRelease financialRelease) {
        this.descricao = financialRelease.getDescription();
        this.valor = financialRelease.getValue();
        this.data = financialRelease.getCreatedAt();
    }
}
