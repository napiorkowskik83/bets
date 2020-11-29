package com.crud.bets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Bet {
    User user;
    BetProspect betProspect;
    Winner tippedWinner;
    BigDecimal stake;
    Boolean finalized;
    Winner winner;
    Boolean won;
    BigDecimal cashWin;
}
