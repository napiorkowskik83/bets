package com.crud.bets.controllers;

import com.crud.bets.domain.DeletedBet;
import com.crud.bets.domain.DeletedBetDto;
import com.crud.bets.domain.LogInAttemptDto;
import com.crud.bets.mapper.DeletedBetMapper;
import com.crud.bets.mapper.LogInAttemptMapper;
import com.crud.bets.services.DeletedBetService;
import com.crud.bets.services.LogInAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class DeletedBetController {

    private final DeletedBetService service;
    private final DeletedBetMapper mapper;

    @Autowired
    public DeletedBetController(DeletedBetService service, DeletedBetMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/deletedbets")
    public List<DeletedBetDto> getAllDeletedBets() {
        return mapper.mapToDeletedBetDtoList(service.getAllDeletedBets());
    }

    @GetMapping(value = "/deletedbets/{userId}")
    public List<DeletedBetDto> getAllDeletedBetsOfUser(@PathVariable Long userId) {
        return mapper.mapToDeletedBetDtoList(service.getAllDeletedBetsOfUser(userId));
    }

  }
