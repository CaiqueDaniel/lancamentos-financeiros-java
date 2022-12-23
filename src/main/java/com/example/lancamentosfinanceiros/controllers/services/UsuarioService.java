package com.example.lancamentosfinanceiros.controllers.services;

import com.example.lancamentosfinanceiros.controllers.dtos.UsuarioDto;
import com.example.lancamentosfinanceiros.models.Usuario;

public interface UsuarioService {
    Usuario create(UsuarioDto usuarioDto);
}
