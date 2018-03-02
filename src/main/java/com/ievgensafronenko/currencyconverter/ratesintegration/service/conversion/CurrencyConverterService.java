package com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.Rate;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.integration.RateService;
import com.ievgensafronenko.currencyconverter.usermanagement.service.registration.UserService;
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
    private RateService rateService;

    @Autowired
    private PreviousConversionsStorageService previousConversionsStorageService;

    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

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

        Double result = calculateResult(amount, rateFrom, rateTo);

        logger.debug("Conversion result for {} / {} is {}", currencyFrom, currencyTo, result);

        String userEmail = userService.loggedUserEmail();

        previousConversionsStorageService
                .save(new PreviousConversions(userEmail,
                        currencyFrom,
                        currencyTo,
                        amount,
                        result, new Date()));
        return result;
    }

    private Double calculateResult(Double amount, Double rateFrom, Double rateTo) {
        return (amount / rateFrom) * rateTo;
    }
}
