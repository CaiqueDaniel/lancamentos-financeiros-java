package com.example.lancamentosfinanceiros.controllers.services.implementation;

import com.example.lancamentosfinanceiros.controllers.dtos.UsuarioDto;
import com.example.lancamentosfinanceiros.controllers.services.UsuarioService;
import com.example.lancamentosfinanceiros.exceptions.UsuarioExisteException;
import com.example.lancamentosfinanceiros.models.Usuario;
import com.example.lancamentosfinanceiros.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceProvider implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario create(UsuarioDto usuarioDto) {
        Optional<Usuario> response = this.repository.findByEmail(usuarioDto.email);

        if (response.isPresent())
            throw new UsuarioExisteException();

        Usuario usuario = new Usuario(usuarioDto);
        return this.repository.saveAndFlush(usuario);
    }
}
