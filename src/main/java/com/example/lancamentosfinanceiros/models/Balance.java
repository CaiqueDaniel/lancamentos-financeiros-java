package com.example.lancamentosfinanceiros.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "balances")
public class Balance extends Model {
    @OneToOne
    private User user;

    @Column(nullable = false)
    @Setter
    private BigDecimal value;

    public Balance(User user, BigDecimal value) {
        this.user = user;
        this.value = value;
    }
}
