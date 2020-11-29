package com.crud.bets.apis.theoddsapi;

import com.crud.bets.apis.theoddsapi.config.TheOddsApiConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheOddsApiClientTestSuite {

    @Autowired
    TheOddsApiClient apiClient;

    @Autowired
    TheOddsApiConfig apiConfig;

    @Test
    public void testGetCurrentOddsProspectDtosFrom(){

        //Given
        //When
        List<OddsApiBetProspectDto> prospectList = apiClient.getCurrentOddsProspectDtosFrom(apiConfig.getLaLigaKey());

        //Then
        System.out.println(prospectList.size() + " matches to stake: \n");

        for (OddsApiBetProspectDto prospect: prospectList){
            System.out.println(prospect.getCommence_time().toLocalDateTime() + " sites qty: " + prospect.getSites().size());
            System.out.println(prospect.getTeams().get(0) + " vs. " + prospect.getTeams().get(1) + "\n");
        }

        assertNotEquals(0, prospectList.size());
    }
}