package com.crud.bets.apis.theoddsapi.facade;

import com.crud.bets.apis.theoddsapi.OddsApiBetProspect;
import com.crud.bets.apis.theoddsapi.TheOddsApiClient;
import com.crud.bets.domain.BetProspectDto;
import com.crud.bets.mapper.OddsBetProspectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetProspectFacade {

    private final TheOddsApiClient oddsApiClient;
    private final OddsBetProspectMapper mapper;

    @Autowired
    public BetProspectFacade(TheOddsApiClient oddsApiClient, OddsBetProspectMapper mapper) {
        this.oddsApiClient = oddsApiClient;
        this.mapper = mapper;
    }

    public List<BetProspectDto> getCurrentBetProspectDtoList(String sportKey){
        List<OddsApiBetProspect> prospectList = oddsApiClient.getCurrentOddsProspectFrom(sportKey);
        ChronoUnit seconds = ChronoUnit.SECONDS;
        prospectList = prospectList.stream()
                .filter(prospect -> seconds.between(prospect.getCommence_time(), ZonedDateTime.now()) < 0)
                .collect(Collectors.toList());
        return mapper.mapFromOddsToBetProspectDtoList(prospectList);
    }
}
