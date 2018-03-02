package com.ievgensafronenko.currencyconverter.config;

import com.ievgensafronenko.currencyconverter.ratesintegration.dto.ConvertDTO;
import com.ievgensafronenko.currencyconverter.usermanagement.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Configuration
@ControllerAdvice
public class InitialDataLoad {

    @Value("#{'${currencies}'.split(',')}")
    private List<String> currencies;

    @Value("#{'${countries}'.split(',')}")
    private List<String> countries;

    @ModelAttribute
    public void populateCurrencies(Model model) {
        model.addAttribute("currencies", currencies);
    }

    @ModelAttribute
    public void populateCountries(Model model) {
        model.addAttribute("countries", countries);
    }

    @ModelAttribute
    public void initConvertDTO(Model model) {
        model.addAttribute("convert", new ConvertDTO());
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
}
