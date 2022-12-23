package com.example.lancamentosfinanceiros.controllers.services;

import com.example.lancamentosfinanceiros.exceptions.SaldoNotFoundException;
import com.example.lancamentosfinanceiros.models.Saldo;
import com.example.lancamentosfinanceiros.models.Usuario;
import com.example.lancamentosfinanceiros.repositories.SaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SaldoService {
    @Autowired
    private SaldoRepository repository;

    public Saldo findOne(Usuario usuario) {
        Optional<Saldo> response = this.repository.findByUsuario(usuario);

        if (response.isEmpty())
            throw new SaldoNotFoundException();

        return response.get();
    }

    public Saldo create(BigDecimal valor) {
        Saldo saldo = new Saldo();

        return saldo;
    }
}
