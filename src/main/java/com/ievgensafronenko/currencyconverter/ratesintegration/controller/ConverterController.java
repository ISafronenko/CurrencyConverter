package com.ievgensafronenko.currencyconverter.ratesintegration.controller;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.model.HistoryData;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.CurrencyConverterService;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    HistoryService historyService;

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

        Double convertResult = currencyConverter.convert(convertDTO);

        List<HistoryData> all = historyService.findAll();
        model.addAttribute("histDatas", all);
        model.addAttribute("convertResult", convertResult);

        return "index";
    }
}
