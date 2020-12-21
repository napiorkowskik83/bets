package com.crud.bets.controllers;

import com.crud.bets.domain.LogInAttemptDto;
import com.crud.bets.domain.UserBalanceChangeDto;
import com.crud.bets.domain.UserDataChangeDto;
import com.crud.bets.mapper.LogInAttemptMapper;
import com.crud.bets.mapper.UserBalanceChangeMapper;
import com.crud.bets.mapper.UserDataChangeMapper;
import com.crud.bets.services.LogInAttemptService;
import com.crud.bets.services.UserChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class LogInAttemptController {

    private final LogInAttemptService service;
    private final LogInAttemptMapper mapper;

    @Autowired
    public LogInAttemptController(LogInAttemptService service, LogInAttemptMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/loginattampts")
    public List<LogInAttemptDto> getLoginAttempts() {
        return mapper.mapToLogInAttemptDtoList(service.getAllLogInAttempts());
    }

    @GetMapping(value = "/loginattampts/{userId}/{onlySuccessful}")
    public List<LogInAttemptDto> getLoginAttemptsOfUser(@PathVariable Long userId, @PathVariable Boolean onlySuccessful) {
        if (onlySuccessful) {
            return mapper.mapToLogInAttemptDtoList(service.getFailedLogInAttemptsOfUser(userId));
        }
        return mapper.mapToLogInAttemptDtoList(service.getAllLogInAttemptsOfUser(userId));
    }

}
