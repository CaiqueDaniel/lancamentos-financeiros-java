package com.example.lancamentosfinanceiros.models;

import com.example.lancamentosfinanceiros.controllers.dtos.UsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@NoArgsConstructor
@Getter
public class Usuario extends Model {
    @Setter
    @Column(nullable = false)
    private String nome;

    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    public Usuario(UsuarioDto usuarioDto) {
        this.nome = usuarioDto.nome;
        this.email = usuarioDto.email;

        this.setSenha(usuarioDto.senha);
    }

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }
}
