package com.crud.bets.mapper;

import com.crud.bets.apis.CompetitionsMap;
import com.crud.bets.domain.BetProspect;
import com.crud.bets.apis.theoddsapi.OddsApiBetProspectDto;
import com.crud.bets.apis.theoddsapi.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BetProspectMapper {

    private CompetitionsMap competitionsMap;

    @Autowired
    public BetProspectMapper(CompetitionsMap competitionsMap) {
        this.competitionsMap = competitionsMap;
    }


    public BetProspect mapToBetProspect(OddsApiBetProspectDto oddsApiBetProspect) {
        Integer sport_key = null;
        if(competitionsMap.getCompetitions().containsKey(oddsApiBetProspect.getSport_key())){
            sport_key = competitionsMap.getCompetitions().get(oddsApiBetProspect.getSport_key()).getId();
        }
        List<String> teams = new ArrayList<>();
        List<BigDecimal> h2h = new ArrayList<>();
        BigDecimal homeTeamWinOdd;
        BigDecimal drawOdd;
        BigDecimal awayTeamWinOdd;
        int sitesQty = 0;
        Double homeTeamWinOddSum = 0.00;
        Double drawOddSum = 0.00;
        Double awayTeamWinOddSum = 0.00;

        if (oddsApiBetProspect.getHome_team().equals(oddsApiBetProspect.getTeams().get(0))) {
            teams.add(oddsApiBetProspect.getTeams().get(0));
            teams.add(oddsApiBetProspect.getTeams().get(1));

            for (Site site : oddsApiBetProspect.getSites()) {
                homeTeamWinOddSum += site.getOdds().getH2h().get(0);
                drawOddSum += site.getOdds().getH2h().get(1);
                awayTeamWinOddSum += site.getOdds().getH2h().get(2);
                sitesQty++;
            }
            homeTeamWinOdd = new BigDecimal(homeTeamWinOddSum/sitesQty).setScale(2, RoundingMode.HALF_UP);
            drawOdd = new BigDecimal(drawOddSum/sitesQty).setScale(2, RoundingMode.HALF_UP);
            awayTeamWinOdd = new BigDecimal(awayTeamWinOddSum/sitesQty).setScale(2, RoundingMode.HALF_UP);
        } else {
            teams.add(oddsApiBetProspect.getTeams().get(1));
            teams.add(oddsApiBetProspect.getTeams().get(0));

            for (Site site : oddsApiBetProspect.getSites()) {
                homeTeamWinOddSum += site.getOdds().getH2h().get(2);
                drawOddSum += site.getOdds().getH2h().get(1);
                awayTeamWinOddSum += site.getOdds().getH2h().get(0);
                sitesQty++;
            }
            homeTeamWinOdd = new BigDecimal(homeTeamWinOddSum/sitesQty).setScale(2, RoundingMode.HALF_UP);
            drawOdd = new BigDecimal(drawOddSum/sitesQty).setScale(2, RoundingMode.HALF_UP);
            awayTeamWinOdd = new BigDecimal(awayTeamWinOddSum/sitesQty).setScale(2, RoundingMode.HALF_UP);
        }
        h2h.add(homeTeamWinOdd);
        h2h.add(drawOdd);
        h2h.add(awayTeamWinOdd);

        ZonedDateTime commence_time = oddsApiBetProspect.getCommence_time();

        BetProspect betProspect = new BetProspect();
        betProspect.setSport_key(sport_key);
        betProspect.setTeams(teams);
        betProspect.setCommence_time(commence_time);
        betProspect.setH2h(h2h);

        return betProspect;
    }

    public List<BetProspect> mapToBetProspectList(List<OddsApiBetProspectDto> oddsApiBetProspectDtoList){
        return oddsApiBetProspectDtoList.stream()
                .map(oddsApiBetProspectDto -> mapToBetProspect(oddsApiBetProspectDto))
                .collect(Collectors.toList());
    }
}
