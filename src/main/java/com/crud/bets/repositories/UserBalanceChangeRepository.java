package com.crud.bets.repositories;


import com.crud.bets.domain.Bet;
import com.crud.bets.domain.UserBalanceChange;
import com.crud.bets.domain.UserDataChange;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserBalanceChangeRepository extends CrudRepository<UserBalanceChange, Long>{

        @Override
        List<UserBalanceChange> findAll();

        @Override
        UserBalanceChange save(UserBalanceChange userBalanceChange);

        @Override
        Optional<UserBalanceChange> findById(Long id);

        @Query(nativeQuery = true)
        List<UserBalanceChange> getAllBalanceChangesOfUser(@Param("USER_ID") Long userId);
}
