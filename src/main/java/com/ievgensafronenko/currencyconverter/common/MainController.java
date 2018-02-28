package com.ievgensafronenko.currencyconverter.common;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.ConvertDTO;
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

    @Value("#{'${currencies}'.split(',')}")
    private List<String> currencies;

    @GetMapping("/")
    public String root(Model model)
    {
        model.addAttribute("convert", new ConvertDTO());
        model.addAttribute("currencies", currencies);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
