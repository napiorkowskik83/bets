package com.crud.bets.controllers;

import com.crud.bets.domain.BetProspectsRequestDto;
import com.crud.bets.domain.LogInAttemptDto;
import com.crud.bets.mapper.BetProspectsRequestMapper;
import com.crud.bets.mapper.LogInAttemptMapper;
import com.crud.bets.repositories.UserRepository;
import com.crud.bets.services.BetProspectsRequestService;
import com.crud.bets.services.LogInAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class BetProspectsRequestController {

    private final BetProspectsRequestService service;
    private final BetProspectsRequestMapper mapper;

    @Autowired
    public BetProspectsRequestController(BetProspectsRequestService service, BetProspectsRequestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/betprospectsrequests")
    public List<BetProspectsRequestDto> getAllBetProspectsRequests() {
        return mapper.mapToBetProspectsRequestDtoList(service.getAllBetProspectsRequests());
    }

    @GetMapping(value = "/betprospectsrequests/{userId}")
    public List<BetProspectsRequestDto>  getLoginAttemptsOfUser(@PathVariable Long userId) {
        return mapper.mapToBetProspectsRequestDtoList(service.getBetProspectsRequestsOfUser(userId));
    }

  }
