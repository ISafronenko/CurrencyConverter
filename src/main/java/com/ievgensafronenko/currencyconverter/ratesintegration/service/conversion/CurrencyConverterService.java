package com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion;

import com.ievgensafronenko.currencyconverter.common.service.ConfigService;
import com.ievgensafronenko.currencyconverter.ratesintegration.dto.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.dto.RateDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.entities.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.integration.RateService;
import com.ievgensafronenko.currencyconverter.usermanagement.service.registration.UserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CurrencyConverterService {

    private RateService rateService;
    private PreviousConversionsStorageService previousConversionsStorageService;
    private UserService userService;
    private ConfigService configService;

    @Autowired
    public CurrencyConverterService(RateService rateService,
                                    PreviousConversionsStorageService previousConversionsStorageService,
                                    UserService userService, ConfigService configService) {
        this.rateService = rateService;
        this.previousConversionsStorageService = previousConversionsStorageService;
        this.userService = userService;
        this.configService = configService;
    }

    /**
     * Method for currency converting.
     *
     * @param convertDTO data transfer object for currency convert data
     * @return result of conversion.
     */
    public Double convert(ConvertDTO convertDTO) {

        log.debug("Starting conversion for following data: {}", convertDTO);

        String currencyFrom = convertDTO.getCurrencyFrom();
        String currencyTo = convertDTO.getCurrencyTo();
        Double amount = convertDTO.getAmount();
        Date dateOfRate = convertDTO.getDate();

        RateDTO rates = getRateDTO(dateOfRate);
        Map<String, Double> ratesMap = rates.getRates();

        Double rateFrom = ratesMap.get(currencyFrom);
        Double rateTo = ratesMap.get(currencyTo);

        Double result = calculateResult(amount, rateFrom, rateTo);

        log.debug("Conversion result for {} / {} is {}", currencyFrom, currencyTo, result);

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

        String pattern = configService.getDateFormat();

        if (dateOfRate.before(new Date())) {
            String date = new SimpleDateFormat(pattern).format(dateOfRate);
            rates = rateService.getRates(date);
        } else {
            rates = rateService.getRates();
        }

        return rates;
    }

    private Double calculateResult(Double amount, Double rateFrom, Double rateTo) {
        return (amount / rateFrom) * rateTo;
    }
}
