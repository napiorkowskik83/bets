package com.crud.bets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BetDto {

    private Long id;
    UserDto user;
    BetProspectDto betProspect;
    LocalDateTime created;
    Winner tippedWinner;
    BigDecimal stake;
    Boolean finalized;
    Winner winner;
    Boolean won;
    BigDecimal cashWin;

}

