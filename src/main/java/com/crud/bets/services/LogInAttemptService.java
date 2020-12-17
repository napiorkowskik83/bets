package com.crud.bets.services;

import com.crud.bets.domain.LogInAttempt;
import com.crud.bets.domain.UserBalanceChange;
import com.crud.bets.domain.UserDataChange;
import com.crud.bets.repositories.LogInAttemptRepository;
import com.crud.bets.repositories.UserBalanceChangeRepository;
import com.crud.bets.repositories.UserDataChangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInAttemptService {

    private final LogInAttemptRepository repository;


    public LogInAttemptService(LogInAttemptRepository repository) {
        this.repository = repository;
    }

    public LogInAttempt addLogInAttempt(LogInAttempt logInAttempt) {
        return repository.save(logInAttempt);
    }

    public List<LogInAttempt> getAllLogInAttempts() {
        return repository.findAll();
    }

    public List<LogInAttempt> getAllLogInAttemptsOfUser(Long userId) {
        return repository.getAllLogInAttemptsOfUser(userId);
    }

    public List<LogInAttempt> getFailedLogInAttemptsOfUser(Long userId) {
        return repository.getFailedLogInAttemptsOfUser(userId);
    }
}

