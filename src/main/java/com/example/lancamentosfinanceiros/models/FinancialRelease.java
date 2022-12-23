package com.example.lancamentosfinanceiros.models;

import com.example.lancamentosfinanceiros.controllers.dtos.RequestFinancialReleaseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class FinancialRelease extends Model {
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    public FinancialRelease(RequestFinancialReleaseDto lancamentoDto, User user) {
        this.user = user;
        this.valor = lancamentoDto.valor;
        this.descricao = lancamentoDto.descricao;
        this.data = lancamentoDto.data;
    }
}
