package com.crud.bets.services;

import com.crud.bets.domain.UserBalanceChange;
import com.crud.bets.domain.UserDataChange;
import com.crud.bets.repositories.UserBalanceChangeRepository;
import com.crud.bets.repositories.UserDataChangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChangeService {

    private final UserDataChangeRepository dataChangeRepository;
    private final UserBalanceChangeRepository balanceChangeRepository;

    public UserChangeService(UserDataChangeRepository dataChangeRepository, UserBalanceChangeRepository balanceChangeRepository) {
        this.dataChangeRepository = dataChangeRepository;
        this.balanceChangeRepository = balanceChangeRepository;
    }

    public UserDataChange addUserDataChange(UserDataChange userDataChange) {
        return dataChangeRepository.save(userDataChange);
    }

    public List<UserDataChange> getAllUsersDataChanges() {
        return dataChangeRepository.findAll();
    }

    public List<UserDataChange> getDataChangesOfUser(Long userId) {
        return dataChangeRepository.getDataChangesOfUser(userId);
    }

    public UserBalanceChange addUserBalanceChange(UserBalanceChange userBalanceChange) {
        return balanceChangeRepository.save(userBalanceChange);
    }

    public List<UserBalanceChange> getAllUsersBalanceChanges() {
        return balanceChangeRepository.findAll();
    }

    public List<UserBalanceChange> getBalanceChangesOfUser(Long userId) {
        return balanceChangeRepository.getBalanceChangesOfUser(userId);
    }
}
