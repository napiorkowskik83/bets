package com.crud.bets.services;

import com.crud.bets.domain.LogInAttempt;
import com.crud.bets.domain.Role;
import com.crud.bets.domain.User;
import com.crud.bets.repositories.LogInAttemptRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogInAttemptServiceTests {

    @InjectMocks
    LogInAttemptService service;

    @Mock
    LogInAttemptRepository repository;

    @Test
    public void testAddLogInAttempt() {
        //Given
        User user = new User(1L, "Test_user1", "Test1@test.com",
                "test_password", Role.USER, LocalDate.now(), new BigDecimal("100"));
        LogInAttempt attempt = new LogInAttempt(1L, user, "login", false, LocalDateTime.now());

        when(repository.save(attempt)).thenReturn(attempt);

        //When
        LogInAttempt retrievedAttempt = service.addLogInAttempt(attempt);

        //Then
        assertEquals(attempt, retrievedAttempt);

    }

    @Test
    public void testGetAllLogInAttempts() {
        //Given
        User user = new User(1L, "Test_user1", "Test1@test.com",
                "test_password", Role.USER, LocalDate.now(), new BigDecimal("100"));
        LogInAttempt attempt1 = new LogInAttempt(1L, user, "login", false, LocalDateTime.now());
        LogInAttempt attempt2 = new LogInAttempt(2L, user, "login", true, LocalDateTime.now());
        List<LogInAttempt> attempts = new ArrayList<>();
        attempts.add(attempt1);
        attempts.add(attempt2);

        when(repository.findAll()).thenReturn(attempts);

        //When
        List<LogInAttempt> retrievedAttempts = service.getAllLogInAttempts();

        //Then
        assertEquals(attempts, retrievedAttempts);
    }

    @Test
    public void testGetAllLogInAttemptsOfUser() {
        //Given
        Long userId = 1L;
        User user = new User(userId, "Test_user1", "Test1@test.com",
                "test_password", Role.USER, LocalDate.now(), new BigDecimal("100"));
        LogInAttempt attempt1 = new LogInAttempt(1L, user, "login", false, LocalDateTime.now());
        LogInAttempt attempt2 = new LogInAttempt(2L, user, "login", true, LocalDateTime.now());
        List<LogInAttempt> attempts = new ArrayList<>();
        attempts.add(attempt1);
        attempts.add(attempt2);

        when(repository.getAllLogInAttemptsOfUser(userId)).thenReturn(attempts);

        //When
        List<LogInAttempt> retrievedAttempts = service.getAllLogInAttemptsOfUser(userId);

        //Then
        assertEquals(attempts, retrievedAttempts);
    }

    @Test
    public void testGetFailedLogInAttemptsOfUser() {
        //Given
        Long userId = 1L;
        User user = new User(userId, "Test_user1", "Test1@test.com",
                "test_password", Role.USER, LocalDate.now(), new BigDecimal("100"));
        LogInAttempt attempt1 = new LogInAttempt(1L, user, "login", false, LocalDateTime.now());
        LogInAttempt attempt2 = new LogInAttempt(2L, user, "login", false, LocalDateTime.now());
        List<LogInAttempt> attempts = new ArrayList<>();
        attempts.add(attempt1);
        attempts.add(attempt2);

        when(repository.getFailedLogInAttemptsOfUser(userId)).thenReturn(attempts);

        //When
        List<LogInAttempt> retrievedAttempts = service.getFailedLogInAttemptsOfUser(userId);

        //Then
        assertEquals(attempts, retrievedAttempts);
    }
}