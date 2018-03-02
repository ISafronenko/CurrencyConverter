package com.ievgensafronenko.currencyconverter.common;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion.PreviousConversionsStorageService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Main controller for managing thymeleaf pages.
 */
@Controller
public class MainController {

    @Autowired
    private PreviousConversionsStorageService previousConversionsStorageService;

    @Autowired
    private Logger logger;

    private static final String PREVIOUS_CONVERSIONS = "previousConversions";

    @GetMapping("/")
    public String root(Model model) {
        logger.debug("Redirecting to index.");

        //TODO move to ControllerAdvice
        List<PreviousConversions> previousConversions = previousConversionsStorageService
                .findByUserEmailOrderByDateDesc();
        model.addAttribute(PREVIOUS_CONVERSIONS, previousConversions);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        logger.debug("Redirecting to login.");
        return "login";
    }
}
