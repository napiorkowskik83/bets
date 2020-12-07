package com.crud.bets.controllers;

import com.crud.bets.domain.BetProspectDtoList;
import com.crud.bets.apis.theoddsapi.facade.BetProspectFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class BetProspectController {

    private final BetProspectFacade service;

    @Autowired
    public BetProspectController(BetProspectFacade service) {
        this.service = service;
    }

    @GetMapping(value = "/betprospects")
    public BetProspectDtoList getCurrentBetProspects(@RequestParam String sportKey){
        return new BetProspectDtoList(service.getCurrentBetProspectDtoList(sportKey));
    }
}
