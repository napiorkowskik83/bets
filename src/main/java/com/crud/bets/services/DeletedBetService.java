package com.crud.bets.services;

import com.crud.bets.domain.DeletedBet;
import com.crud.bets.domain.LogInAttempt;
import com.crud.bets.repositories.DeletedBetRepository;
import com.crud.bets.repositories.LogInAttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletedBetService {

    private final DeletedBetRepository repository;


    public DeletedBetService(DeletedBetRepository repository) {
        this.repository = repository;
    }

    public DeletedBet addDeletedBet(DeletedBet deletedBet) {
        return repository.save(deletedBet);
    }

    public List<DeletedBet> getAllDeletedBets() {
        return repository.findAll();
    }

    public List<DeletedBet> getAllDeletedBetsOfUser(Long userId) {
        return repository.getAllDeletedBetsOfUser(userId);
    }
}

