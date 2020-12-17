package com.crud.bets.mapper;

import com.crud.bets.domain.UserBalanceChange;
import com.crud.bets.domain.UserBalanceChangeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserBalanceChangeMapper {

    public UserBalanceChangeDto mapToUserBalanceChange(UserBalanceChange userBalanceChange) {
        return new UserBalanceChangeDto(userBalanceChange.getId(), userBalanceChange.getUser().getId(),
                userBalanceChange.getOldValue(), userBalanceChange.getNewValue(), userBalanceChange.getChangeTime());
    }

    public List<UserBalanceChangeDto> mapToUserBalanceChangeList(List<UserBalanceChange> userBalanceChangeList) {
        return userBalanceChangeList.stream().map(userBalanceChange -> mapToUserBalanceChange(userBalanceChange))
                .collect(Collectors.toList());
    }
}
