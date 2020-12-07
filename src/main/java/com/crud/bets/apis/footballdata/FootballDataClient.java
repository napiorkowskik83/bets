package com.crud.bets.apis.footballdata;

import com.crud.bets.apis.footballdata.config.FootballDataApiConfig;
import com.crud.bets.domain.Bet;
import com.crud.bets.domain.BetProspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class FootballDataClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FootballDataClient.class);
    private final FootballDataApiConfig dataApiConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public FootballDataClient(FootballDataApiConfig dataApiConfig, RestTemplate restTemplate) {
        this.dataApiConfig = dataApiConfig;
        this.restTemplate = restTemplate;
    }

    public List<Match> getDailyMatchesResults(Bet bet){

        URI url = createUriForGetDailyMatchesResults(bet);

        HttpHeaders headers = new HttpHeaders();
        headers.set(dataApiConfig.getFootballDataApiHeader(), dataApiConfig.getFootballDataApiToken());
        HttpEntity entity = new HttpEntity(headers);

        try{
            ResponseEntity<Matches> responseEntity = restTemplate.exchange(
                    url, HttpMethod.GET, entity, Matches.class);
            List<Match> matchList = ofNullable(responseEntity.getBody().getMatches()).orElse(new ArrayList<>());
            return matchList;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private URI createUriForGetDailyMatchesResults(Bet bet) {
        BetProspect betProspect = bet.getBetProspect();
        URI url = UriComponentsBuilder.fromHttpUrl(dataApiConfig.getFootballDataApiEndpoint() +
                "/competitions/" + betProspect.getSport_key() + "/matches")
                .queryParam("dateFrom", betProspect.getCommence_time().toLocalDate())
                .queryParam("dateTo", betProspect.getCommence_time().toLocalDate())
                .build().encode().toUri();
        return url;
    }
}
