package com.crud.bets.apis.theoddsapi;

import com.crud.bets.apis.theoddsapi.OddsApiBetProspectDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OddsApiBetProspectDtoList {
    private List<OddsApiBetProspectDto> data;
}
