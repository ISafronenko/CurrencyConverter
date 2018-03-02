package com.ievgensafronenko.currencyconverter.ratesintegration.service.integration;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.Rate;

/**
 * Interface which define type for integrations with rate exchange services.
 */
public interface RateService {

    /**
     * Method for getting rates.
     *
     * @return - rates.
     */
    Rate getRates();
}
