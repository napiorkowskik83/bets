package com.crud.bets.apis.footballdata;

import com.crud.bets.apis.TeamsMap;
import com.crud.bets.domain.BetProspect;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FootballDataClientTestSuite {

    @Autowired
    FootballDataClient dataClient;

    @Autowired
    TeamsMap teamsMap;

    @Test
    public void getDailyMatchesResults() {
        //Given

        Integer sport_key = 2014;
        List<String> teams = new ArrayList<>();
        teams.add("Elche CF");
        teams.add("Cádiz CF");
        ZonedDateTime commence_time = ZonedDateTime.parse("2020-11-28T13:00:00Z");
        List<BigDecimal> h2h = new ArrayList<>();
        h2h.add(new BigDecimal("3.56"));
        h2h.add(new BigDecimal("3.94"));
        h2h.add(new BigDecimal("4.18"));

        BetProspect betProspect = new BetProspect( sport_key, teams, commence_time, h2h);

        //When
        List<Match> matches = dataClient.getDailyMatchesResults(betProspect);

        for(Match match: matches){
            System.out.println(match.getHomeTeam().getName() + "  "
                            + match.getScore().getFullTime().getHomeTeamGoals()
                            + " : " + match.getScore().getFullTime().getAwayTeamGoals()
                            + "  " + match.getAwayTeam().getName()
                            + "  " + match.getStatus() + "  winner: "
                            + match.getScore().getWinner()
            );

            if(match.getHomeTeam().getId().equals(teamsMap.getTeams().get(betProspect.getTeams().get(0)))
                    && match.getAwayTeam().getId().equals(teamsMap.getTeams().get(betProspect.getTeams().get(1)))
                    && match.getStatus().equals("FINISHED")){
                System.out.println(" - matching game");
            }
        }

        //Then
        Assert.assertNotEquals(0, matches.size());

        for(Match match: matches){
            System.out.println(match.getHomeTeam().getName() + "  "
                    + match.getScore().getFullTime().getHomeTeamGoals()
                    + " : " + match.getScore().getFullTime().getAwayTeamGoals()
                    + "  " + match.getAwayTeam().getName()
                    + "  " + match.getStatus() + "  winner: "
                    + match.getScore().getWinner()
            );

            if(match.getHomeTeam().getId().equals(teamsMap.getTeams().get(betProspect.getTeams().get(0)))
                    && match.getAwayTeam().getId().equals(teamsMap.getTeams().get(betProspect.getTeams().get(1)))
                    && match.getStatus().equals("FINISHED")){
                System.out.println(" - matching game");
            }
        }
    }
}