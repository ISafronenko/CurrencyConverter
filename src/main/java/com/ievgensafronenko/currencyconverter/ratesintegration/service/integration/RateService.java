package com.ievgensafronenko.currencyconverter.ratesintegration.service.integration;

import com.ievgensafronenko.currencyconverter.ratesintegration.dto.RateDTO;

/**
 * Interface which define type for integrations with rate exchange services.
 */
public interface RateService {

    /**
     * Method for getting today rates.
     *
     * @return - rates.
     */
    RateDTO getRates();

    /**
     * Method for getting rates for specific date
     *
     * @param date string with date in 2001-02-16 format.
     * @return rates
     */
    RateDTO getRates(String date);
}
