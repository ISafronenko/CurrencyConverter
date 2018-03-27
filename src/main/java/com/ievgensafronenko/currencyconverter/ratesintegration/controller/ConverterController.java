package com.ievgensafronenko.currencyconverter.ratesintegration.controller;

import com.ievgensafronenko.currencyconverter.ratesintegration.dto.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.entities.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion.CurrencyConverterService;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion.PreviousConversionsStorageService;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.validation.CurrencyConverterValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for handling currency converting.
 */
@Controller
@Api(value = "Currency converter resource", description = "This is currency converting.")
@RequestMapping("/convert")
@Slf4j
public class ConverterController {

    private static final String CONVERT_RESULT = "convertResult";
    private static final String PREVIOUS_CONVERSIONS = "previousConversions";

    private CurrencyConverterService currencyConverter;
    private PreviousConversionsStorageService previousConversionsStorageService;
    private CurrencyConverterValidationService validationService;

    @Autowired
    public ConverterController(CurrencyConverterService currencyConverter, PreviousConversionsStorageService previousConversionsStorageService, CurrencyConverterValidationService validationService) {
        this.currencyConverter = currencyConverter;
        this.previousConversionsStorageService = previousConversionsStorageService;
        this.validationService = validationService;
    }

    @ModelAttribute("convert")
    public ConvertDTO convertDTO() {
        return new ConvertDTO();
    }

    @GetMapping
    public String redirectToIndex() {
        return "index";
    }

    @ApiOperation(value = "Endpoint for converting currency.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid request format"),
                    @ApiResponse(code = 500, message = "Temporary technical server error")
            }
    )
    @PostMapping
    public String convert(@ModelAttribute("convert") @Valid ConvertDTO convertDTO,
                          BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors() || validationService.validate(convertDTO, bindingResult)) {
            addPreviousConversionsToResponse(model);
            log.error("ConvertDTO Validation failed.");
            return "index";
        }

        calculateResult(convertDTO, model);

        return "index";
    }

    /**
     * Method for adding results of calculation to entities.
     * Also, we are adding previous conversion data to entities.
     *
     * @param convertDTO - dto with form data.
     * @param model      - entities.
     */
    private void calculateResult(ConvertDTO convertDTO, Model model) {
        Double convertResult = currencyConverter.convert(convertDTO);
        addPreviousConversionsToResponse(model);
        model.addAttribute(CONVERT_RESULT, convertResult);
    }

    /**
     * Method for adding results of previous conversions to entities.
     *
     * @param model updated entities.
     */
    private void addPreviousConversionsToResponse(Model model) {
        List<PreviousConversions> previousConversions = previousConversionsStorageService
                .findByUserEmailOrderByDateDesc();
        model.addAttribute(PREVIOUS_CONVERSIONS, previousConversions);
    }
}
