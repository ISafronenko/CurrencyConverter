package com.ievgensafronenko.currencyconverter.ratesintegration.service;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.Rate;
import com.ievgensafronenko.currencyconverter.usermanagement.service.UserService;
import org.slf4j.Logger;
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
    PreviousConversionsStorageService previousConversionsStorageService;

    @Autowired
    UserService userService;

    @Autowired
    Logger logger;

    /**
     * Method for currency converting.
     *
     * @param convertDTO data transfer object for currency convert data
     * @return result of conversion.
     */
    public Double convert(ConvertDTO convertDTO) {

        logger.debug("Starting conversion for following data: {}", convertDTO);

        Rate rates = rateService.getRates();
        Map<String, Double> ratesMap = rates.getRates();

        String currencyFrom = convertDTO.getCurrencyFrom();
        String currencyTo = convertDTO.getCurrencyTo();
        Double amount = convertDTO.getAmount();

        logger.debug("Getting conversion rates from {} / to {}.", currencyFrom, currencyTo);
        Double rateFrom = ratesMap.get(currencyFrom);
        Double rateTo = ratesMap.get(currencyTo);

        logger.debug("Conversion rate between USD and Currency From {} is {}", currencyFrom, rateFrom);
        logger.debug("Conversion rate between USD and Currency To {} is {}", currencyFrom, rateTo);

        Double result = (amount / rateFrom) * rateTo;
        Double formattedResult = (double) Math.round(result * 1000d) / 1000d;

        logger.debug("Conversion result for {} / {} is {}", currencyFrom, currencyTo, formattedResult);

        String userEmail = userService.loggedUserEmail();

        previousConversionsStorageService
                .save(new PreviousConversions(userEmail,
                        currencyFrom,
                        currencyTo,
                        amount,
                        formattedResult, new Date()));
        return formattedResult;
    }
}
