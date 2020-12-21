package com.crud.bets.services;

import com.crud.bets.controllers.UserNotFoundException;
import com.crud.bets.domain.BetProspectsRequest;
import com.crud.bets.domain.BetProspectsRequestDto;
import com.crud.bets.domain.Role;
import com.crud.bets.domain.User;
import com.crud.bets.mapper.BetProspectsRequestMapper;
import com.crud.bets.repositories.BetProspectsRequestRepository;
import com.crud.bets.repositories.UserRepository;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BetProspectsRequestServiceTests {

    @InjectMocks
    private BetProspectsRequestService requestService;

    @Mock
    private BetProspectsRequestRepository prospectsRequestRepository;

    @Mock
    private BetProspectsRequestMapper prospectsRequestMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testAddBetProspectsRequest() throws UserNotFoundException {

        //Given
        List<String> leagues = new ArrayList<>();
        leagues.add("La Liga - Spain");

        BetProspectsRequestDto requestDto = new BetProspectsRequestDto(1L, 1L, leagues, LocalDateTime.now());
        User user = new User(1L, "Test_user1", "Test1@test.com",
                "test_password", Role.USER, LocalDate.now(), new BigDecimal("200"));

        BetProspectsRequest request = new BetProspectsRequest(1L, user, leagues, LocalDateTime.now());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(prospectsRequestMapper.mapToBetProspectsRequest(requestDto, user)).thenReturn(request);
        when(prospectsRequestRepository.save(request)).thenReturn(request);

        //When
        BetProspectsRequest retrievedRequest = requestService.addBetProspectsRequest(requestDto);

        //Then

        assertEquals(request, retrievedRequest);
    }

    @Test
    public void testGetAllBetProspectsRequests() {
        //Given
        User user = new User(1L, "Test_user1", "Test1@test.com",
                "test_password", Role.USER, LocalDate.now(), new BigDecimal("200"));

        List<String> leagues = new ArrayList<>();
        leagues.add("La Liga - Spain");

        BetProspectsRequest request1 = new BetProspectsRequest(1L, user, leagues, LocalDateTime.now());
        BetProspectsRequest request2 = new BetProspectsRequest(2L, user, leagues, LocalDateTime.now().minusHours(2));

        List<BetProspectsRequest> requests = new ArrayList<>();
        requests.add(request1);
        requests.add(request2);

        when(prospectsRequestRepository.findAll()).thenReturn(requests);

        //When
        List<BetProspectsRequest> retrievedRequests = requestService.getAllBetProspectsRequests();

        //Then
        assertEquals(2, retrievedRequests.size());
        assertEquals(request1, retrievedRequests.get(0));
        assertEquals(request2, retrievedRequests.get(1));
    }

    @Test
    public void testGetBetProspectsRequestsOfUser() {
        //Given
        Long userId = 1L;
        User user = new User(userId, "Test_user1", "Test1@test.com",
                "test_password", Role.USER, LocalDate.now(), new BigDecimal("200"));

        List<String> leagues = new ArrayList<>();
        leagues.add("La Liga - Spain");

        BetProspectsRequest request1 = new BetProspectsRequest(1L, user, leagues, LocalDateTime.now());
        BetProspectsRequest request2 = new BetProspectsRequest(2L, user, leagues, LocalDateTime.now().minusHours(2));

        List<BetProspectsRequest> requests = new ArrayList<>();
        requests.add(request1);
        requests.add(request2);

        when(prospectsRequestRepository.getBetProspectsRequestsOfUser(userId)).thenReturn(requests);

        //When
        List<BetProspectsRequest> retrievedRequests = requestService.getBetProspectsRequestsOfUser(userId);

        //Then
        assertEquals(2, retrievedRequests.size());
        assertEquals(request1, retrievedRequests.get(0));
        assertEquals(request2, retrievedRequests.get(1));
    }
}