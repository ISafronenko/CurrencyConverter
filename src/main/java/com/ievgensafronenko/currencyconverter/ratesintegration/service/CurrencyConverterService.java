package com.ievgensafronenko.currencyconverter.ratesintegration.service;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertingResultDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service for currency converting
 */
@Service
public class CurrencyConverterService {

    @Autowired
    RateService rateService;

    /**
     * Method for currency converting.
     *
     * @param currencyFrom - currency code from which converting.
     * @param amount       - amount.
     * @param currencyTo   - currency code to which converting.
     * @return ConvertingResultDTO object.
     */
    public ConvertingResultDTO convert(String currencyFrom, Double amount, String currencyTo) {
        Rate rates = rateService.getRates();

        Map<String, Double> ratesMap = rates.getRates();

        Double rateFrom = ratesMap.get(currencyFrom);
        Double rateTo = ratesMap.get(currencyTo);
        Double result = (amount / rateFrom) * rateTo;

        return new ConvertingResultDTO(currencyFrom, currencyTo, amount, result);
    }
}
