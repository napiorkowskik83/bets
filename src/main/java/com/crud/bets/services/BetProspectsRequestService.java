package com.crud.bets.services;

import com.crud.bets.controllers.UserNotFoundException;
import com.crud.bets.domain.BetProspectsRequest;
import com.crud.bets.domain.BetProspectsRequestDto;
import com.crud.bets.domain.LogInAttempt;
import com.crud.bets.domain.User;
import com.crud.bets.mapper.BetProspectsRequestMapper;
import com.crud.bets.repositories.BetProspectsRequestRepository;
import com.crud.bets.repositories.LogInAttemptRepository;
import com.crud.bets.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetProspectsRequestService {

    private final BetProspectsRequestRepository prospectsRequestRepository;
    private final BetProspectsRequestMapper prospectsRequestMapper;
    private final UserRepository userRepository;



    public BetProspectsRequestService(BetProspectsRequestRepository prospectsRequestRepository,
                                      BetProspectsRequestMapper prospectsRequestMapper, UserRepository userRepository) {
        this.prospectsRequestRepository = prospectsRequestRepository;
        this.prospectsRequestMapper = prospectsRequestMapper;
        this.userRepository = userRepository;
    }

    public BetProspectsRequest addBetProspectsRequest(BetProspectsRequestDto prospectsRequestDto) throws UserNotFoundException {
        User user = userRepository.findById(prospectsRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        BetProspectsRequest request = prospectsRequestMapper.mapToBetProspectsRequest(prospectsRequestDto, user);
        return prospectsRequestRepository.save(request);
    }

    public List<BetProspectsRequest> getAllBetProspectsRequests() {
        return prospectsRequestRepository.findAll();
    }

    public List<BetProspectsRequest> getBetProspectsRequestsOfUser(Long userId) {
        return prospectsRequestRepository.getBetProspectsRequestsOfUser(userId);
    }
}

