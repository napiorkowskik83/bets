package com.crud.bets.services;

import com.crud.bets.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTests {

    @InjectMocks
    private SimpleEmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void sendBetsWonInfo() {
        //Given
        Mail mail = new Mail("napiorkowskik83@gmail.com", "Bets test e-mail", "Bets test message");

        //When
        emailService.sendBetsWonInfo(mail, "Kris");

        //Then
        verify(javaMailSender, times(1)).send((MimeMessagePreparator) any());
    }
}
