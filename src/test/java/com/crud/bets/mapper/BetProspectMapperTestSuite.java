package com.crud.bets.mapper;

import com.crud.bets.apis.CompetitionsMap;
import com.crud.bets.apis.theoddsapi.ApiOdds;
import com.crud.bets.domain.BetProspect;
import com.crud.bets.apis.theoddsapi.OddsApiBetProspectDto;
import com.crud.bets.apis.theoddsapi.Site;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BetProspectMapperTestSuite {

    @Autowired
    BetProspectMapper betProspectMapper;

    @Autowired
    CompetitionsMap competitionsMap;

    @Test
    public void mapToBetProspect() {
        //Given
        List<Double> h2h1 = new ArrayList<>();
        h2h1.add(5.45);
        h2h1.add(3.58);
        h2h1.add(4.24);
        ApiOdds apiOdds1 = new ApiOdds(h2h1);
        Site site1 = new Site(apiOdds1);

        List<Double> h2h2 = new ArrayList<>();
        h2h2.add(5.65);
        h2h2.add(3.18);
        h2h2.add(4.44);
        ApiOdds apiOdds2 = new ApiOdds(h2h2);
        Site site2 = new Site(apiOdds2);

        List<Site> sites =new ArrayList<>();
        sites.add(site1);
        sites.add(site2);

        List<String> teams = new ArrayList<>();
        teams.add("Real Madrid");
        teams.add("Barcelona");

        ZonedDateTime commenceTime = ZonedDateTime.parse("2020-11-28T18:30:50Z");

        OddsApiBetProspectDto apiBetProspect = new OddsApiBetProspectDto(
                "soccer_spain_la_liga", teams, "Real Madrid", commenceTime, sites);

        //When
        BetProspect betProspect = betProspectMapper.mapToBetProspect(apiBetProspect);

        //Then
        Assert.assertEquals(commenceTime, betProspect.getCommence_time());
        System.out.println(
                betProspect.getSport_key() + "\n" +
                betProspect.getTeams() + "\n" +
                betProspect.getH2h() + "\n" +
                betProspect.getCommence_time() + "\n"
        );
    }
}