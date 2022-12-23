package com.example.lancamentosfinanceiros.controllers.dtos;

import com.example.lancamentosfinanceiros.models.FinancialRelease;

public class ResponseFinancialReleaseDto extends RequestFinancialReleaseDto {
    public ResponseFinancialReleaseDto(FinancialRelease financialRelease) {
        this.descricao = financialRelease.getDescricao();
        this.valor = financialRelease.getValor();
        this.data = financialRelease.getData();
    }
}
