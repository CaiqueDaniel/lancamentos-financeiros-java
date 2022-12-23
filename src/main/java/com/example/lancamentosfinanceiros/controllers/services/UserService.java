package com.example.lancamentosfinanceiros.controllers.services;

import com.example.lancamentosfinanceiros.controllers.dtos.RequestUserDto;
import com.example.lancamentosfinanceiros.exceptions.UserConflictException;
import com.example.lancamentosfinanceiros.exceptions.UserNotFoundException;
import com.example.lancamentosfinanceiros.models.Balance;
import com.example.lancamentosfinanceiros.models.User;
import com.example.lancamentosfinanceiros.repositories.BalanceRepository;
import com.example.lancamentosfinanceiros.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BalanceRepository balanceRepository;

    public User create(RequestUserDto usuarioDto) {
        Optional<User> response = this.repository.findByUsername(usuarioDto.email);

        if (response.isPresent())
            throw new UserConflictException();

        User user = new User(usuarioDto);
        user = this.repository.saveAndFlush(user);

        Balance balance = new Balance(user, new BigDecimal(0));
        this.balanceRepository.saveAndFlush(balance);

        return user;
    }

    public User findOne(String username) {
        Optional<User> response = this.repository.findByUsername(username);

        if (response.isEmpty())
            throw new UserNotFoundException();

        return response.get();
    }
}
