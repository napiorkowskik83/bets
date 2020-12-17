package com.crud.bets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeletedBetDto {

    private Long id;
    private Long userId;
    private String hemeTeam;
    private String awayTeam;
    private ZonedDateTime commence_time;
    private Winner tippedWinner;
    private BigDecimal odd;
    private BigDecimal stake;
    private LocalDateTime deleteTime;
}
