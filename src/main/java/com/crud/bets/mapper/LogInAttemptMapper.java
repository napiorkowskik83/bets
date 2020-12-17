package com.crud.bets.mapper;

import com.crud.bets.domain.LogInAttempt;
import com.crud.bets.domain.LogInAttemptDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LogInAttemptMapper {

    public LogInAttemptDto mapToLogInAttemptDto(LogInAttempt logInAttempt){
        return new LogInAttemptDto(logInAttempt.getId(), logInAttempt.getUser().getId(),
                logInAttempt.getLogin(), logInAttempt.getSuccessful(), logInAttempt.getAttemptTime());
    }

    public List<LogInAttemptDto> mapToLogInAttemptDtoList(List<LogInAttempt> logInAttemptList){
        return logInAttemptList.stream().map(logInAttempt -> mapToLogInAttemptDto(logInAttempt))
                .collect(Collectors.toList());
    }
}
