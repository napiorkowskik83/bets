package com.crud.bets.controllers;

import com.crud.bets.domain.*;
import com.crud.bets.mapper.BetMapper;
import com.crud.bets.mapper.UserBalanceChangeMapper;
import com.crud.bets.mapper.UserDataChangeMapper;
import com.crud.bets.services.BetService;
import com.crud.bets.services.UserChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class UserChangeController {

    private final UserChangeService service;
    private final UserDataChangeMapper dataChangeMapper;
    private final UserBalanceChangeMapper balanceChangeMapper;

    @Autowired
    public UserChangeController(UserChangeService service, UserDataChangeMapper dataChangeMapper,
                                UserBalanceChangeMapper balanceChangeMapper) {
        this.service = service;
        this.dataChangeMapper = dataChangeMapper;
        this.balanceChangeMapper = balanceChangeMapper;
    }

    @GetMapping(value = "/userdatachanges")
    public List<UserDataChangeDto> getAllUsersDataChanges() {
        return dataChangeMapper.mapToUserDataChangeDtoList(service.getAllUsersDataChanges());
    }

    @GetMapping(value = "/userdatachanges/{userId}")
    public List<UserDataChangeDto> getDataChangesOfUser(@PathVariable Long userId) {
        return dataChangeMapper.mapToUserDataChangeDtoList(service.getDataChangesOfUser(userId));
    }

    @GetMapping(value = "/userbalancechanges")
    public List<UserBalanceChangeDto> getAllUsersBalanceChanges() {
        return balanceChangeMapper.mapToUserBalanceChangeDtoList(service.getAllUsersBalanceChanges());
    }

    @GetMapping(value = "/userbalancechanges/{userId}")
    public List<UserBalanceChangeDto> getBalanceChangesOfUser(@PathVariable Long userId) {
        return balanceChangeMapper.mapToUserBalanceChangeDtoList(service.getBalanceChangesOfUser(userId));
    }
}
