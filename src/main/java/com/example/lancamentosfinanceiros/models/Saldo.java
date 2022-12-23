package com.example.lancamentosfinanceiros.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Entity
public class Saldo extends Model {
    @OneToOne
    private Usuario usuario;

    @Column(nullable = false)
    private BigDecimal valor;
}
