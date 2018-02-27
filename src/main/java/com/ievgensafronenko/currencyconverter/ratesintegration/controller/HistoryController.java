package com.ievgensafronenko.currencyconverter.ratesintegration.controller;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.HistoryData;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for getting historical data.
 */
@RestController
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/history")
    public List<HistoryData> getHistoryData(){
        return historyService.findAll();
    }
}
