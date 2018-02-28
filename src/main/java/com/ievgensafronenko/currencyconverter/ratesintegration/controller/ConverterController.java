package com.ievgensafronenko.currencyconverter.ratesintegration.controller;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.CurrencyConverterService;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.PreviousConversionsStorageService;
import com.ievgensafronenko.currencyconverter.usermanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    CurrencyConverterService currencyConverter;

    @Autowired
    PreviousConversionsStorageService previousConversionsStorageService;

    @Autowired
    UserService userService;

    @Autowired
    Logger logger;

    @Value("#{'${currencies}'.split(',')}")
    private List<String> currencies;

    @ModelAttribute("convert")
    public ConvertDTO convertDTO() {
        return new ConvertDTO();
    }

    @ApiOperation(value = "Endpoint for converting currency.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid request format"),
                    @ApiResponse(code = 500, message = "Temporary technical server error")
            }
    )
    @PostMapping(path = "/convert")
    public String convert(@ModelAttribute("convert") @Valid ConvertDTO convertDTO,
                          BindingResult result, Model model) {

        logger.debug("ConvertDTO received: {}", convertDTO);

        Double convertResult = currencyConverter.convert(convertDTO);

        String userEmail = userService.loggedUserEmail();

        logger.debug("Getting previous conversions data for user email: {}", userEmail);
        List<PreviousConversions> previousConversions = previousConversionsStorageService
                .findByUserEmailOrderByDateDesc(userEmail);

        if (previousConversions == null || previousConversions.size() == 0) {
            logger.debug("Can't load previous conversions for user email: {}", userEmail);
        }

        int size = previousConversions.size();

        logger.debug("For user email: {} {} records has been found", userEmail, size);
        model.addAttribute("previousConversions", previousConversions);
        model.addAttribute("convertResult", convertResult);

        //TODO Temp solution and need to be changed to @ControllerAdvice.
        model.addAttribute("currencies", currencies);

        logger.debug("Redirecting to index");
        return "index";
    }
}
