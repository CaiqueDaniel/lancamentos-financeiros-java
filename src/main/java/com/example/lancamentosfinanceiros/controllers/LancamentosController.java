package com.example.lancamentosfinanceiros.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lancamentos")
public class LancamentosController {

    @GetMapping("")
    public ResponseEntity index(){
        return ResponseEntity.ok().build();
    }
}
