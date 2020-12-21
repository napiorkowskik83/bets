package com.crud.bets.mapper;

import com.crud.bets.domain.DeletedBet;
import com.crud.bets.domain.DeletedBetDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeletedBetMapper {

    public DeletedBetDto mapToDeletedBetDto(DeletedBet deletedBet) {
        return new DeletedBetDto(deletedBet.getId(), deletedBet.getUser().getId(), deletedBet.getHomeTeam(),
                deletedBet.getAwayTeam(), deletedBet.getCommence_time(), deletedBet.getTippedWinner(),
                deletedBet.getOdd(), deletedBet.getStake(), deletedBet.getDeleteTime());
    }

    public List<DeletedBetDto> mapToDeletedBetDtoList(List<DeletedBet> deletedBetList) {
        return deletedBetList.stream().map(this::mapToDeletedBetDto)
                .collect(Collectors.toList());
    }
}
