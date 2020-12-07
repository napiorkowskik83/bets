package com.crud.bets.mapper;

import com.crud.bets.domain.BetProspect;
import com.crud.bets.domain.BetProspectDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BetProspectMapper {

    public BetProspect mapToBetProspect(BetProspectDto betProspectDto){
        return new BetProspect(betProspectDto.getId(), betProspectDto.getSport_key(), betProspectDto.getTeams(),
                betProspectDto.getCommence_time(), betProspectDto.getH2h());
    }

    public List<BetProspect> mapToBetProspectList(List<BetProspectDto> betProspectDtoList){
        return betProspectDtoList.stream()
                .map(betProspectDto -> mapToBetProspect(betProspectDto))
                .collect(Collectors.toList());
    }

    public BetProspectDto mapToBetProspectDto(BetProspect betProspect){
        return new BetProspectDto(betProspect.getId(), betProspect.getSport_key(), betProspect.getTeams(),
                betProspect.getCommence_time(), betProspect.getH2h());
    }

    public List<BetProspectDto> mapToBetProspectDtoList(List<BetProspect> betProspectList){
        return betProspectList.stream()
                .map(betProspect -> mapToBetProspectDto(betProspect))
                .collect(Collectors.toList());
    }
}
