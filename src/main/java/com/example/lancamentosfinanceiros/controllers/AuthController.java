package com.example.lancamentosfinanceiros.controllers;

import com.example.lancamentosfinanceiros.controllers.dtos.ResponseAuthDto;
import com.example.lancamentosfinanceiros.security.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseAuthDto login(Authentication authentication) {
        return this.tokenService.generateToken(authentication);
    }
}
