package com.example.lancamentosfinanceiros.controllers;

import com.example.lancamentosfinanceiros.controllers.dtos.UsuarioDto;
import com.example.lancamentosfinanceiros.controllers.services.UsuarioService;
import com.example.lancamentosfinanceiros.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UsuarioDto usuarioDto) throws URISyntaxException {
        Usuario usuario = this.service.create(usuarioDto);
        return ResponseEntity.created(new URI("/api/usuario/" + usuario.getId())).build();
    }
}
