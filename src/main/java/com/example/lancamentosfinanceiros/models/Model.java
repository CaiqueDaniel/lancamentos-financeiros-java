package com.example.lancamentosfinanceiros.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@MappedSuperclass
public abstract class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "criado_em", nullable = false)
    @CreationTimestamp
    protected LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    @UpdateTimestamp
    protected LocalDateTime atualizadoEm;
}
