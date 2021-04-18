package com.crud.bets.apis.theoddsapi;

import com.crud.bets.apis.theoddsapi.config.TheOddsApiConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TheOddsApiClientTestSuite {

    @Autowired
    TheOddsApiClient apiClient;

    @Autowired
    TheOddsApiConfig apiConfig;

    @Disabled
    @Test
    public void testGetCurrentOddsProspectDtosFrom() {

        //Given
        //When
        List<OddsApiBetProspect> prospectList = apiClient.getCurrentOddsProspectFrom(apiConfig.getLaLigaKey());

        //Then
        assertNotEquals(0, prospectList.size());
    }
}