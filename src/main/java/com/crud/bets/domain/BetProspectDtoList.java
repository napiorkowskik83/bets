package com.crud.bets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BetProspectDtoList {
    private List<BetProspectDto> list;
}
