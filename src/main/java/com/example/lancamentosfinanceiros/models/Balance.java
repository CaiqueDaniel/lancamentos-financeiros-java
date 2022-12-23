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
public class Balance extends Model {
    @OneToOne
    private User user;

    @Column(nullable = false)
    @Setter
    private BigDecimal valor;

    public Balance(User user, BigDecimal valor) {
        this.user = user;
        this.valor = valor;
    }
}
