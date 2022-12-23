package com.example.lancamentosfinanceiros.controllers;

import com.example.lancamentosfinanceiros.controllers.dtos.RequestLancamentoDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponseLancamentoDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponsePagination;
import com.example.lancamentosfinanceiros.controllers.services.LancamentoService;
import com.example.lancamentosfinanceiros.controllers.services.UsuarioService;
import com.example.lancamentosfinanceiros.models.Lancamento;
import com.example.lancamentosfinanceiros.models.Usuario;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping("/api/lancamento")
@PermitAll
public class LancamentosController {
    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Lancamento> create(@RequestBody RequestLancamentoDto lancamentoDto, Authentication auth) throws URISyntaxException {
        Usuario usuario = this.usuarioService.findOne(auth.getName());
        Lancamento lancamento = this.lancamentoService.create(lancamentoDto, usuario);

        return ResponseEntity.created(new URI("/api/lancamento/" + lancamento.getId())).build();
    }

    @GetMapping
    public ResponseEntity<ResponsePagination<ResponseLancamentoDto>> index(@RequestParam(name = "pagina", defaultValue = "1") int page, Authentication auth) {
        Usuario usuario = this.usuarioService.findOne(auth.getName());
        ResponsePagination<ResponseLancamentoDto> response= this.lancamentoService.findAllFrom(usuario, page);

        return ResponseEntity.ok(response);
    }
}
