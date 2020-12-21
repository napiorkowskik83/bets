package com.crud.bets.apis.footballdata;

import com.crud.bets.apis.TeamsMap;
import com.crud.bets.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public void testGetDailyMatchesResults() {
//        //Given

        Integer sport_key = 2014;
        List<String> teams = new ArrayList<>();
        teams.add("Elche CF");
        teams.add("Cádiz CF");
        ZonedDateTime commence_time = ZonedDateTime.parse("2020-11-28T13:00:00Z");
        List<BigDecimal> h2h = new ArrayList<>();
        h2h.add(new BigDecimal("3.56"));
        h2h.add(new BigDecimal("3.94"));
        h2h.add(new BigDecimal("4.18"));

        BetProspect betProspect = new BetProspect(sport_key, teams, commence_time, h2h);

        User user = new User(1L, "testUser", "testEmail", "testPassword",
                Role.USER, LocalDate.parse("2020-12-01"), new BigDecimal("100"));

        Bet bet = new Bet(1L, user, betProspect, LocalDateTime.parse("2020-11-27T20:00:00"),
                Winner.HOME_TEAM, betProspect.getH2h().get(0), new BigDecimal("55.43"),
                false, null, false, null);
//
//        //When
//        List<Match> matches = dataClient.getDailyMatchesResults(bet);
//
//        //Then
//        Assert.assertNotEquals(0, matches.size());
    }
}