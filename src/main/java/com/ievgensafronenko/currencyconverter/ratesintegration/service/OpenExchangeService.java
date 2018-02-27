package com.ievgensafronenko.currencyconverter.ratesintegration.service;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.Rate;
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

    @Value("${rate.service.url}")
    private String url;

    /**
     * Method for getting rates from rates exchange service.
     *
     * @return rates.
     */
    @Override
    public Rate getRates() {
        Rate rate = restTemplate.getForObject(url, Rate.class);
        System.out.println(rate);
        return rate;
    }
}
