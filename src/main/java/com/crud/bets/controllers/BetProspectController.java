package com.crud.bets.controllers;

import com.crud.bets.domain.BetProspectDtoList;
import com.crud.bets.apis.theoddsapi.facade.BetProspectFacade;
import com.crud.bets.domain.BetProspectsRequestDto;
import com.crud.bets.services.BetProspectsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class BetProspectController {

    private final BetProspectFacade prospectFacade;
    private final BetProspectsRequestService prospectsRequestService;


    @Autowired
    public BetProspectController(BetProspectFacade prospectFacade,
                                 BetProspectsRequestService prospectsRequestService) {
        this.prospectFacade = prospectFacade;
        this.prospectsRequestService = prospectsRequestService;
    }

    @PostMapping(value = "/betprospects")
    public BetProspectDtoList getCurrentBetProspects(@RequestBody BetProspectsRequestDto prospectsRequestDto)
            throws UserNotFoundException {
        prospectsRequestService.addBetProspectsRequest(prospectsRequestDto);
        return new BetProspectDtoList(prospectFacade.getCurrentBetProspectDtoList(prospectsRequestDto));
    }
}
