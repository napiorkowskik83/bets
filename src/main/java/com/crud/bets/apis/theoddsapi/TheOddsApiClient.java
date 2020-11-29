package com.crud.bets.apis.theoddsapi;

import com.crud.bets.apis.theoddsapi.config.TheOddsApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import static java.util.Optional.ofNullable;

@Component
public class TheOddsApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheOddsApiClient.class);
    private final TheOddsApiConfig theOddsApiConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public TheOddsApiClient(TheOddsApiConfig theOddsApiConfig, RestTemplate restTemplate) {
        this.theOddsApiConfig = theOddsApiConfig;
        this.restTemplate = restTemplate;
    }

    public List<OddsApiBetProspectDto> getCurrentOddsProspectDtosFrom(String competitionKey){
        URI url = createUriForGetOddsProspects(competitionKey);

        try{
            OddsApiBetProspectDtoList prospectsResponse = restTemplate.getForObject(url, OddsApiBetProspectDtoList.class);
            return ofNullable(prospectsResponse.getData()).orElse(new ArrayList<>());
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private URI createUriForGetOddsProspects(String competitionKey){
        URI url = UriComponentsBuilder.fromHttpUrl(theOddsApiConfig.getTheOddApiEndpoint() +
                "/odds/")
                .queryParam("sport", competitionKey)
                .queryParam("region", "eu")
                .queryParam("mkt" , "h2h")
                .queryParam("dateFormat", "iso")
                .queryParam("apiKey", theOddsApiConfig.getTheOddApiKey())
                .build().encode().toUri();
        return url;
    }
}
