package com.example.lancamentosfinanceiros.controllers.services;

import com.example.lancamentosfinanceiros.controllers.dtos.UsuarioDto;
import com.example.lancamentosfinanceiros.exceptions.UsuarioExisteException;
import com.example.lancamentosfinanceiros.exceptions.UsuarioNotFoundException;
import com.example.lancamentosfinanceiros.models.Saldo;
import com.example.lancamentosfinanceiros.models.Usuario;
import com.example.lancamentosfinanceiros.repositories.SaldoRepository;
import com.example.lancamentosfinanceiros.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SaldoRepository saldoRepository;

    public Usuario create(UsuarioDto usuarioDto) {
        Optional<Usuario> response = this.repository.findByUsername(usuarioDto.email);

        if (response.isPresent())
            throw new UsuarioExisteException();

        Usuario usuario = new Usuario(usuarioDto);
        usuario = this.repository.saveAndFlush(usuario);

        Saldo saldo = new Saldo(usuario, new BigDecimal(0));
        this.saldoRepository.saveAndFlush(saldo);

        return usuario;
    }

    public Usuario findOne(String username) {
        Optional<Usuario> response = this.repository.findByUsername(username);

        if (response.isEmpty())
            throw new UsuarioNotFoundException();

        return response.get();
    }
}
