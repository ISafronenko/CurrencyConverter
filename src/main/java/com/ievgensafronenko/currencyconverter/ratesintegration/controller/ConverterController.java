package com.ievgensafronenko.currencyconverter.ratesintegration.controller;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion.CurrencyConverterService;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion.PreviousConversionsStorageService;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.validation.CurrencyConverterValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for handling currency converting.
 */
@Controller
@Api(value = "Currency converter resource", description = "This is currency converting.")
public class ConverterController {

    @Autowired
    private CurrencyConverterService currencyConverter;

    @Autowired
    private PreviousConversionsStorageService previousConversionsStorageService;

    @Autowired
    private CurrencyConverterValidationService validationService;

    @Autowired
    private Logger logger;

    @ModelAttribute("convert")
    public ConvertDTO convertDTO() {
        return new ConvertDTO();
    }

    private static final String CONVERT_RESULT = "convertResult";
    private static final String PREVIOUS_CONVERSIONS = "previousConversions";

    @ApiOperation(value = "Endpoint for converting currency.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid request format"),
                    @ApiResponse(code = 500, message = "Temporary technical server error")
            }
    )
    @PostMapping(path = "/convert")
    public String convert(@ModelAttribute("convert") @Valid ConvertDTO convertDTO,
                          BindingResult bindingResult, Model model) {

        logger.debug("ConvertDTO received: {}", convertDTO);

        if (bindingResult.hasErrors() || validationService.validate(convertDTO, bindingResult)) {
            addPreviousConversionsToResponse(model);
            logger.debug("Validation failed redirecting to index.");
            return "index";
        }

        calculateResult(convertDTO, model);

        logger.debug("Redirecting to index");
        return "index";
    }

    /**
     * Method for adding results of calculation to model.
     * Also, we are adding previous conversion data to model.
     *
     * @param convertDTO - dto with form data.
     * @param model      - model.
     */
    private void calculateResult(ConvertDTO convertDTO, Model model) {
        Double convertResult = currencyConverter.convert(convertDTO);
        addPreviousConversionsToResponse(model);
        model.addAttribute(CONVERT_RESULT, convertResult);
    }

    /**
     * Method for adding results of previous conversions to model.
     *
     * @param model updated model.
     */
    private void addPreviousConversionsToResponse(Model model) {
        List<PreviousConversions> previousConversions = previousConversionsStorageService
                .findByUserEmailOrderByDateDesc();
        model.addAttribute(PREVIOUS_CONVERSIONS, previousConversions);
    }
}
