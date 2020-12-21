package com.crud.bets.apis.theoddsapi.facade;

import com.crud.bets.apis.LeaguesMap;
import com.crud.bets.apis.theoddsapi.OddsApiBetProspect;
import com.crud.bets.apis.theoddsapi.TheOddsApiClient;
import com.crud.bets.domain.BetProspectDto;
import com.crud.bets.domain.BetProspectsRequestDto;
import com.crud.bets.mapper.OddsBetProspectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetProspectFacade {

    private final TheOddsApiClient oddsApiClient;
    private final OddsBetProspectMapper mapper;
    private final LeaguesMap leaguesMap;

    @Autowired
    public BetProspectFacade(TheOddsApiClient oddsApiClient, OddsBetProspectMapper mapper,
                             LeaguesMap leaguesMap) {
        this.oddsApiClient = oddsApiClient;
        this.mapper = mapper;
        this.leaguesMap = leaguesMap;
    }

    public List<BetProspectDto> getCurrentBetProspectDtoList(BetProspectsRequestDto prospectsRequestDto) {
        List<OddsApiBetProspect> prospectList = new ArrayList<>();
        for (String league : prospectsRequestDto.getLeagues()) {
            prospectList.addAll(oddsApiClient.getCurrentOddsProspectFrom(leaguesMap.getLeagues().get(league)));
        }

        ChronoUnit seconds = ChronoUnit.SECONDS;
        prospectList = prospectList.stream()
                .filter(prospect -> seconds.between(prospect.getCommence_time(), ZonedDateTime.now()) < 0)
                .collect(Collectors.toList());
        return mapper.mapFromOddsToBetProspectDtoList(prospectList);
    }
}
