package com.ievgensafronenko.currencyconverter.common;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    Logger logger;

    @Value("#{'${currencies}'.split(',')}")
    private List<String> currencies;

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("convert", new ConvertDTO());
        model.addAttribute("currencies", currencies);

        logger.debug("Loaded currencies and redirecting to index.");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        logger.debug("Redirecting to login page.");
        return "login";
    }
}
