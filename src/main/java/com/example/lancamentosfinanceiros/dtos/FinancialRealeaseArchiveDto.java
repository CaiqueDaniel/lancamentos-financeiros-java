package com.example.lancamentosfinanceiros.dtos;

import com.example.lancamentosfinanceiros.models.FinancialRelease;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class FinancialRealeaseArchiveDto {
    public Long userId;
    public BigDecimal value;
    public String description, username, createdAt;

    public FinancialRealeaseArchiveDto(FinancialRelease financialRelease) {
        this.userId = financialRelease.getUser().getId();
        this.username = financialRelease.getUser().getUsername();
        this.value = financialRelease.getValue();
        this.description = financialRelease.getDescription();
        this.createdAt = financialRelease.getCreatedAt().toString();
    }
}
