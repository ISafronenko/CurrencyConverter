package com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion;

import com.ievgensafronenko.currencyconverter.ratesintegration.dto.ConvertDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.dto.RateDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.integration.RateService;
import com.ievgensafronenko.currencyconverter.usermanagement.service.registration.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterServiceTest {

    @InjectMocks
    private CurrencyConverterService converterService;

    @Mock
    private RateService rateService;

    @Mock
    private UserService userService;

    @Mock
    private PreviousConversionsStorageService previousConversionsStorageService;

    @Before
    public void setUp() {
        when(rateService.getRates(anyObject())).thenReturn(getDefaultRates());
        when(rateService.getRates()).thenReturn(getDefaultRates());
        when(userService.loggedUserEmail()).thenReturn("user1@gmail.com");
    }

    @Test
    public void convertEurToEurSuccess() {
        Double result = converterService.convert(getDefaultDto());
        assertTrue(result != null);
        assertTrue(result.equals(1d));
    }

    private ConvertDTO getDefaultDto() {
        ConvertDTO convertDTO = new ConvertDTO();
        convertDTO.setAmount(1d);
        convertDTO.setCurrencyFrom("EUR");
        convertDTO.setCurrencyTo("EUR");
        convertDTO.setDate(new Date());

        return convertDTO;
    }

    private RateDTO getDefaultRates() {
        RateDTO rateDTO = new RateDTO();

        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 1.01d);
        rates.put("USD", 1.d);

        rateDTO.setRates(rates);
        return rateDTO;
    }
}