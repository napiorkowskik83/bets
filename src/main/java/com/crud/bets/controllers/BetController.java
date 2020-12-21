package com.crud.bets.controllers;

import com.crud.bets.domain.BetDto;
import com.crud.bets.domain.BetDtoList;
import com.crud.bets.mapper.BetMapper;
import com.crud.bets.services.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class BetController {

    private final BetService service;
    private final BetMapper mapper;

    @Autowired
    public BetController(BetService service, BetMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/bets")
    public BetDtoList getAllBets() {
        return new BetDtoList(mapper.mapToBetDtoList(service.getAllBets()));
    }

    @GetMapping(value = "/bets/{userId}/{onlyPending}")
    public BetDtoList getBetsOfUser(@PathVariable Long userId, @PathVariable Boolean onlyPending) {
        if (onlyPending) {
            return new BetDtoList(mapper.mapToBetDtoList(service.getPendingBetsOfUser(userId)));
        }
        return new BetDtoList(mapper.mapToBetDtoList(service.getAllBetsOfUser(userId)));
    }


    @PostMapping(value = "/bets", consumes = APPLICATION_JSON_VALUE)
    public void addBet(@RequestBody BetDto betDto) {
        service.addBet(mapper.mapToBet(betDto));
    }

    @DeleteMapping(value = "/bets/{betId}")
    public void deleteBet(@PathVariable Long betId) {
        service.deleteBet(betId);
    }
}
