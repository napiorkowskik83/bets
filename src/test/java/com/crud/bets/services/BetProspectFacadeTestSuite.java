package com.crud.bets.services;

import com.crud.bets.apis.theoddsapi.facade.BetProspectFacade;
import com.crud.bets.domain.BetProspectDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BetProspectFacadeTestSuite {

    @Autowired
    BetProspectFacade betProspectFacade;

    @Test
    public void TestGetCurrentBetProspectDtoList() {
        //Given
        //When
        List<BetProspectDto> list = betProspectFacade.getCurrentBetProspectDtoList("soccer_spain_la_liga");
        //Then
        for(BetProspectDto prospect: list){
            System.out.println(prospect.getCommence_time() + " " + prospect.getTeams().get(0) + " vs " +
                    prospect.getTeams().get(1) + " " + prospect.getH2h());
        }




    }
}