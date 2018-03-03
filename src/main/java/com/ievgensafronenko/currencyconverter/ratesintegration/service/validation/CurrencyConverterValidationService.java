package com.ievgensafronenko.currencyconverter.ratesintegration.service.validation;

import com.ievgensafronenko.currencyconverter.ratesintegration.dto.ConvertDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Date;

/**
 * Service for conversion data validation.
 */
@Service
public class CurrencyConverterValidationService {

    @Autowired
    private Logger logger;

    @Value("${latest.date}")
    private Date latestDate;

    /**
     * Method for validating input from conversion form.
     *
     * @param convertDTO - object which contains conversion data.
     * @param result     - binding result.
     * @return - true if validation has failed, false otherwise.
     */
    public boolean validate(ConvertDTO convertDTO, BindingResult result) {

        logger.debug("Validating input before currency conversions.");

        boolean isValidationFailed = false;

        if (convertDTO.getDate() == null) {
            logger.error("Date cannot be null");
            result.rejectValue("date", null, "Date cannot be null.");
            isValidationFailed = true;
        } else if (convertDTO.getDate().before(latestDate)) {
            logger.error("Date should be after 01/01/1999");
            result.rejectValue("date", null, "Historical are available only from Jan 1st, 1999.");
            isValidationFailed = true;
        } else if (convertDTO.getAmount() <= 0) {
            logger.error("Amount cannot be negative");
            result.rejectValue("amount", null, "Amount cannot be negative or 0");
            isValidationFailed = true;
        } else if (result.hasErrors()) {
            isValidationFailed = true;
        }

        logger.debug("Is validation failed: {}", isValidationFailed);

        return isValidationFailed;
    }

}
