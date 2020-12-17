package com.crud.bets.apis.footballdata;

import com.crud.bets.domain.Winner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Score {
    private Winner winner;
    private FullTime fullTime;
}
