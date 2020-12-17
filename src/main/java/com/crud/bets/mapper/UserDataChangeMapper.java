package com.crud.bets.mapper;

import com.crud.bets.domain.UserDataChange;
import com.crud.bets.domain.UserDataChangeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataChangeMapper {

    public UserDataChangeDto mapToUserDataChange(UserDataChange userDataChange) {
        return new UserDataChangeDto(userDataChange.getId(), userDataChange.getUser().getId(),
                userDataChange.getChangedValue(), userDataChange.getOldValue(),
                userDataChange.getNewValue(), userDataChange.getChangeTime());
    }

    public List<UserDataChangeDto> mapToUserDataChangeList(List<UserDataChange> userDataChangeList) {
        return userDataChangeList.stream().map(userDataChange -> mapToUserDataChange(userDataChange))
                .collect(Collectors.toList());
    }
}
