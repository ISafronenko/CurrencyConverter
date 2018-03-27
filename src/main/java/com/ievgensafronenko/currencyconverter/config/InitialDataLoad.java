package com.ievgensafronenko.currencyconverter.config;

import com.ievgensafronenko.currencyconverter.common.service.ConfigService;
import com.ievgensafronenko.currencyconverter.ratesintegration.dto.ConvertDTO;
import com.ievgensafronenko.currencyconverter.usermanagement.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Configuration
@ControllerAdvice
public class InitialDataLoad {

    @Autowired
    private ConfigService configService;

    @ModelAttribute
    public void populateCurrencies(Model model) {
        model.addAttribute("currencies", configService.getCurrencies());
    }

    @ModelAttribute
    public void populateCountries(Model model) {
        model.addAttribute("countries", configService.getCountries());
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
