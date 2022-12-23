package com.example.lancamentosfinanceiros.controllers;

import com.example.lancamentosfinanceiros.controllers.dtos.RequestUserDto;
import com.example.lancamentosfinanceiros.controllers.services.UserService;
import com.example.lancamentosfinanceiros.models.User;
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
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RequestUserDto usuarioDto) throws URISyntaxException {
        User user = this.service.create(usuarioDto);
        return ResponseEntity.created(new URI("/api/usuario/" + user.getId())).build();
    }
}
