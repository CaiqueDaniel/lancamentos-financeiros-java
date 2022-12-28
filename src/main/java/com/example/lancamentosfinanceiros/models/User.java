package com.example.lancamentosfinanceiros.models;

import com.example.lancamentosfinanceiros.dtos.requests.RequestUserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "users")
public class User extends Model implements UserDetails {
    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "account_expired", nullable = false)
    private boolean accountExpired;

    @Column(name = "account_locked", nullable = false)
    private boolean accountLocked;

    @Column(name = "credential_expired", nullable = false)
    private boolean credentialsExpired;

    @Column(nullable = false)
    private boolean enabled;

    public User(RequestUserDto userDto) {
        this.enabled = true;
        this.credentialsExpired = false;
        this.accountLocked = false;
        this.accountExpired = false;

        this.name = userDto.nome;
        this.username = userDto.email;

        this.setPassword(userDto.senha);
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
