package com.crud.bets.scheduler;

import com.crud.bets.domain.User;
import com.crud.bets.repositories.UserRepository;
import com.crud.bets.services.BetsReviewer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BetsReviewScheduler {

    private final BetsReviewer betsReviewer;
    private final UserRepository userRepository;

    public BetsReviewScheduler(BetsReviewer betsReviewer, UserRepository userRepository) {
        this.betsReviewer = betsReviewer;
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 13-23 * * *")
    public void reviewPendingBets(){
        List<User> users = userRepository.findAll();
        for (User user: users){
            betsReviewer.updateBetsStatus(user.getId());
        }
    }
}
