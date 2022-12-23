package com.example.lancamentosfinanceiros.models;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@MappedSuperclass
@Getter
public abstract class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "criado_em")
    @CreationTimestamp
    protected LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    @UpdateTimestamp
    protected LocalDateTime atualizadoEm;
}
