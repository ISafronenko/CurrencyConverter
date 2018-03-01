package com.ievgensafronenko.currencyconverter.common;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main controller for managing thymeleaf pages.
 */
@Controller
public class MainController {

    @Autowired
    Logger logger;

    @GetMapping("/")
    public String root() {
        logger.debug("Redirecting to index.");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        logger.debug("Redirecting to login.");
        return "login";
    }
}
