package com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion;

import com.ievgensafronenko.currencyconverter.ratesintegration.dto.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.dto.RateDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.entities.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.integration.RateService;
import com.ievgensafronenko.currencyconverter.usermanagement.service.registration.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private static final DateFormat FORMAT = new SimpleDateFormat("YYYY-MM-dd");

    /**
     * Method for currency converting.
     *
     * @param convertDTO data transfer object for currency convert data
     * @return result of conversion.
     */
    public Double convert(ConvertDTO convertDTO) {

        logger.debug("Starting conversion for following data: {}", convertDTO);

        String currencyFrom = convertDTO.getCurrencyFrom();
        String currencyTo = convertDTO.getCurrencyTo();
        Double amount = convertDTO.getAmount();
        Date dateOfRate = convertDTO.getDate();

        RateDTO rates = getRateDTO(dateOfRate);
        Map<String, Double> ratesMap = rates.getRates();

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
                        result,
                        new Date(),
                        dateOfRate
                ));

        return result;
    }

    /**
     * Method for getting rates based on date.
     *
     * @param dateOfRate - date of rate.
     * @return rates.
     */
    private RateDTO getRateDTO(Date dateOfRate) {

        RateDTO rates;

        if (dateOfRate.before(new Date())) {
            String date = FORMAT.format(dateOfRate);
            logger.debug("Getting historical rates for date {}", date);
            rates = rateService.getRates(date);
        } else {
            logger.debug("Getting actual rates");
            rates = rateService.getRates();
        }

        return rates;
    }

    private Double calculateResult(Double amount, Double rateFrom, Double rateTo) {
        return (amount / rateFrom) * rateTo;
    }
}
