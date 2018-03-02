package com.ievgensafronenko.currencyconverter.ratesintegration.service.integration;

import com.ievgensafronenko.currencyconverter.ratesintegration.dto.RateDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service for getting rates from openexchangerates.org
 */
@Service
public class OpenExchangeService implements RateService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Logger logger;

    @Autowired
    private Environment env;

    @Value("${rate.service.url}")
    private String url;

    /**
     * Method for getting rates from rates exchange service.
     *
     * @return rates.
     */
    @Override
    public RateDTO getRates() {
        String openexchangerates_key = env.getProperty("openexchangerates_key");
        logger.debug("Loading rates from openexchangerates.org");
        RateDTO rateDTO = restTemplate.getForObject(url+ openexchangerates_key, RateDTO.class);
        logger.debug("Loaded rates from openexchangerates.org: \n {}");
        return rateDTO;
    }
}
