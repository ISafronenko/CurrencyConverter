package com.ievgensafronenko.currencyconverter.ratesintegration.service.integration;

import com.ievgensafronenko.currencyconverter.common.service.ConfigService;
import com.ievgensafronenko.currencyconverter.ratesintegration.dto.RateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service for getting rates from openexchangerates.org
 */
@Service
@Slf4j
public class OpenExchangeService implements RateService {
    private RestTemplate restTemplate;
    private Environment env;
    private ConfigService configService;

    @Autowired
    public OpenExchangeService(RestTemplate restTemplate,
                               Environment env,
                               ConfigService configService) {
        this.restTemplate = restTemplate;
        this.env = env;
        this.configService = configService;
    }

    /**
     * Method for getting rates from rates exchange service.
     *
     * @return rates.
     */
    @Override
    public RateDTO getRates() {
        log.debug("Loading actual rates from openexchangerates.org");

        String rateUrl = configService.getLatestRateUrl() + getKey();
        RateDTO rateDTO = requestRates(rateUrl);
        log.debug("Loaded rates from openexchangerates.org: \n {}");
        return rateDTO;
    }

    /**
     * Method for getting rates from rates exchange service for specific date
     *
     * @param date string with date in 2001-02-16 format.
     * @return rates.
     */
    @Override
    public RateDTO getRates(String date) {
        String rateUrl = configService.getHistoricalRateUrl().replace("{}", date) + getKey();
        return requestRates(rateUrl);
    }

    private RateDTO requestRates(String rateUrl) {
        return restTemplate.getForObject(rateUrl, RateDTO.class);
    }

    private String getKey() {
        return env.getProperty("openexchangerates_key");
    }
}
