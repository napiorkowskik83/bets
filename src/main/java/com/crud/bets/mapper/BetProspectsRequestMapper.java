package com.crud.bets.mapper;

import com.crud.bets.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BetProspectsRequestMapper {

    public BetProspectsRequestDto mapToBetProspectsRequestDto(BetProspectsRequest prospectsRequest){
        return new BetProspectsRequestDto(prospectsRequest.getId(), prospectsRequest.getUser().getId(),
                prospectsRequest.getLeagues(), prospectsRequest.getCreated());
    }

    public List<BetProspectsRequestDto> mapToBetProspectsRequestDtoList(List<BetProspectsRequest> prospectsRequestList){
        return prospectsRequestList.stream().map(prospectsRequest -> mapToBetProspectsRequestDto(prospectsRequest))
                .collect(Collectors.toList());
    }

    public BetProspectsRequest mapToBetProspectsRequest(BetProspectsRequestDto prospectsRequestDto, User user){
        return new BetProspectsRequest(prospectsRequestDto.getId(), user,
                prospectsRequestDto.getLeagues(), prospectsRequestDto.getCreated());
    }
}
