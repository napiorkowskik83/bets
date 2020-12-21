package com.crud.bets.apis.theoddsapi;

import com.crud.bets.apis.theoddsapi.config.TheOddsApiConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheOddsApiClientTestSuite {

    @Autowired
    TheOddsApiClient apiClient;

    @Autowired
    TheOddsApiConfig apiConfig;

    @Test
    public void testGetCurrentOddsProspectDtosFrom() {

        //Given
        //When
//        List<OddsApiBetProspect> prospectList = apiClient.getCurrentOddsProspectFrom(apiConfig.getLaLigaKey());
//
//        //Then
//        assertNotEquals(0, prospectList.size());
    }
}