package com.example.lancamentosfinanceiros.models;

import com.example.lancamentosfinanceiros.dtos.requests.RequestFinancialReleaseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "financial_releases")
public class FinancialRelease extends Model {
    @ManyToOne
    protected User user;

    @Column(nullable = false)
    protected BigDecimal value;

    @Column(nullable = false)
    protected String description;

    public FinancialRelease(RequestFinancialReleaseDto financialReleaseDto, User user) {
        this.user = user;
        this.value = financialReleaseDto.valor;
        this.description = financialReleaseDto.descricao;
    }
}
