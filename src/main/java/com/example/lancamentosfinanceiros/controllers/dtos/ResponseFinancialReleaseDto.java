package com.example.lancamentosfinanceiros.controllers.dtos;

import com.example.lancamentosfinanceiros.models.FinancialRelease;

public class ResponseFinancialReleaseDto extends RequestFinancialReleaseDto {
    public ResponseFinancialReleaseDto(FinancialRelease financialRelease) {
        this.descricao = financialRelease.getDescription();
        this.valor = financialRelease.getValue();
        this.data = financialRelease.getDate();
    }
}
