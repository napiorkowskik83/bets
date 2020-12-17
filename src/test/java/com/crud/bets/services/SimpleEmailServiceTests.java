package com.crud.bets.services;

import com.crud.bets.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleEmailServiceTests {

    @Autowired
    SimpleEmailService emailService;

    @Test
    public void sendBetsWonInfo() {
        //Given
        Mail mail = new Mail("napiorkowskik83@gmail.com", "Bets test e-mail", "Bets test message");

        emailService.sendBetsWonInfo(mail, "Kris");
    }
}
