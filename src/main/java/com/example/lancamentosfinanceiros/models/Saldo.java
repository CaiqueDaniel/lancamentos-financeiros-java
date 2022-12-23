package com.example.lancamentosfinanceiros.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Saldo extends Model {
    @OneToOne
    private Usuario usuario;

    @Column(nullable = false)
    @Setter
    private BigDecimal valor;

    public Saldo(Usuario usuario, BigDecimal valor) {
        this.usuario = usuario;
        this.valor = valor;
    }
}
