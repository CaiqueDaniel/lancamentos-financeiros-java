package com.example.lancamentosfinanceiros.models;

import com.example.lancamentosfinanceiros.controllers.dtos.RequestFinancialReleaseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class FinancialRelease extends Model {
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private String description;

    public FinancialRelease(RequestFinancialReleaseDto financialReleaseDto, User user) {
        this.user = user;
        this.value = financialReleaseDto.valor;
        this.description = financialReleaseDto.descricao;
    }
}
