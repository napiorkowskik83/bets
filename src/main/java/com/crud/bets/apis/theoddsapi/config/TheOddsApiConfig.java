package com.crud.bets.apis.theoddsapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TheOddsApiConfig {
    @Value("${theoddsapi.api.endpoint.prod}")
    private String theOddApiEndpoint;

    @Value("${theoddsapi.app.apiKey}")
    private String theOddApiKey;

    private String ELPKey = "soccer_epl";
    private String LaLigaKey = "soccer_spain_la_liga";
    private String SerieAKey = "soccer_italy_serie_a";
}
