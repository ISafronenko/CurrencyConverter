package com.ievgensafronenko.currencyconverter.ratesintegration.service;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.HistoryData;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.Rate;
import com.ievgensafronenko.currencyconverter.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Service for currency converting
 */
@Service
public class CurrencyConverterService {

    @Autowired
    RateService rateService;

    @Autowired
    HistoryService historyService;

    @Autowired
    UserService userService;

    /**
     * Method for currency converting.
     *
     * @param currencyFrom - currency code from which converting.
     * @param amount       - amount.
     * @param currencyTo   - currency code to which converting.
     * @return ConvertDTO object.
     */
    public Double convert(ConvertDTO convertDTO) {
        Rate rates = rateService.getRates();

        Map<String, Double> ratesMap = rates.getRates();

        String currencyFrom = convertDTO.getCurrencyFrom();
        String currencyTo = convertDTO.getCurrencyTo();
        Double amount = convertDTO.getAmount();

        Double rateFrom = ratesMap.get(currencyFrom);
        Double rateTo = ratesMap.get(currencyTo);
        Double result = (amount / rateFrom) * rateTo;

        //TODO needs to be refactored.
        historyService.save(new HistoryData("", currencyFrom, currencyTo, amount, result, new Date()));

        return result;
    }
}
