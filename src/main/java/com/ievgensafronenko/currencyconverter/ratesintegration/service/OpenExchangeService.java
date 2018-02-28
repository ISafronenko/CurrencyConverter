package com.ievgensafronenko.currencyconverter.ratesintegration.service;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.Rate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service for getting rates from openexchangerates.org
 */
@Service
public class OpenExchangeService implements RateService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Logger logger;

    @Value("${rate.service.url}")
    private String url;

    /**
     * Method for getting rates from rates exchange service.
     *
     * @return rates.
     */
    @Override
    public Rate getRates() {
        logger.debug("Loading rates from openexchangerates.org");
        Rate rate = restTemplate.getForObject(url, Rate.class);
        logger.debug("Loaded rates from openexchangerates.org: \n {}");
        return rate;
    }
}
