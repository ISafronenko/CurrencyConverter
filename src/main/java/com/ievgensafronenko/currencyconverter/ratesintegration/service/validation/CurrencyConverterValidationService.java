package com.ievgensafronenko.currencyconverter.ratesintegration.service.validation;

import com.ievgensafronenko.currencyconverter.ratesintegration.dto.ConvertDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 * Service for conversion data validation.
 */
@Service
public class CurrencyConverterValidationService {

    @Autowired
    private Logger logger;

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
