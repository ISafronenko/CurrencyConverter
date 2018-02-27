package com.ievgensafronenko.currencyconverter.ratesintegration.controller;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertingResultDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.CurrencyConverterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling currency converting.
 */
@RestController
@Api(value = "Currency converter resource", description = "This is currency converting.")
public class ConverterController {

    @Autowired
    CurrencyConverterService currencyConverter;

    @ApiOperation(value = "Endpoint for converting currency.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid request format"),
                    @ApiResponse(code = 500, message = "Temporary technical server error")
            }
    )
    @GetMapping(path = "/convert/{currencyFrom}/{amount}/{currencyTo}")
    public ConvertingResultDTO convert(@PathVariable String currencyFrom,
                                       @PathVariable Double amount,
                                       @PathVariable String currencyTo) {

        return currencyConverter.convert(currencyFrom, amount, currencyTo);
    }
}
