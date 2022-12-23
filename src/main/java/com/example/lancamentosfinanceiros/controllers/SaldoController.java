package com.example.lancamentosfinanceiros.controllers;

import com.example.lancamentosfinanceiros.controllers.dtos.ResponseSaldoDto;
import com.example.lancamentosfinanceiros.controllers.services.SaldoService;
import com.example.lancamentosfinanceiros.controllers.services.UsuarioService;
import com.example.lancamentosfinanceiros.models.Saldo;
import com.example.lancamentosfinanceiros.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saldo")
public class SaldoController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SaldoService saldoService;

    @GetMapping
    public ResponseEntity<ResponseSaldoDto> view(Authentication auth) {
        Usuario usuario = this.usuarioService.findOne(auth.getName());
        Saldo saldo = this.saldoService.findOne(usuario);

        return ResponseEntity.ok(new ResponseSaldoDto(saldo));
    }

    /*@PutMapping
    public ResponseEntity<Saldo> edit(@RequestBody RequestSaldoDto saldoDto, Authentication auth) {

    }*/
}
