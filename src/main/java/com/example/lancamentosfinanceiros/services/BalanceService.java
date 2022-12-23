package com.example.lancamentosfinanceiros.services;

import com.example.lancamentosfinanceiros.exceptions.BalanceNotFoundException;
import com.example.lancamentosfinanceiros.models.Balance;
import com.example.lancamentosfinanceiros.models.User;
import com.example.lancamentosfinanceiros.repositories.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository repository;

    public Balance findOneBy(User user) {
        Optional<Balance> response = this.repository.findByUser(user);

        if (response.isEmpty())
            throw new BalanceNotFoundException();

        return response.get();
    }

    public Balance update(Balance balance) {
        return this.repository.saveAndFlush(balance);
    }
}
