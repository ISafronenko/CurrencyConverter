package com.ievgensafronenko.currencyconverter.common.controller;

import com.ievgensafronenko.currencyconverter.ratesintegration.entities.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion.PreviousConversionsStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Main controller for managing thymeleaf pages.
 */
@Controller
@Slf4j
public class MainController {

    private static final String PREVIOUS_CONVERSIONS = "previousConversions";

    @Autowired
    private PreviousConversionsStorageService previousConversionsStorageService;

    @GetMapping("/")
    public String root(Model model) {
        log.debug("Redirecting to index.");

        List<PreviousConversions> previousConversions = previousConversionsStorageService
                .findByUserEmailOrderByDateDesc();
        model.addAttribute(PREVIOUS_CONVERSIONS, previousConversions);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        log.debug("Redirecting to login.");
        return "login";
    }
}
