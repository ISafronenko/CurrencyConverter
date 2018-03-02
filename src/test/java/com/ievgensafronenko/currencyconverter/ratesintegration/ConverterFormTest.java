package com.ievgensafronenko.currencyconverter.ratesintegration;

import com.ievgensafronenko.currencyconverter.ratesintegration.dto.RateDTO;
import com.ievgensafronenko.currencyconverter.ratesintegration.service.integration.RateService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class ConverterFormTest {

    @Mock
    private RateService rateService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        RateDTO rateDTO = new RateDTO();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 1.1d);
        rates.put("USD", 1d);
        rateDTO.setRates(rates);
        when(rateService.getRates()).thenReturn(rateDTO);
    }

    @Ignore
    @Test
    @WithMockUser(username = "user1@email.com", roles = "USER")
    public void successConvertTest() throws Exception {
        this.mockMvc
                .perform(
                        post("/convert")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("currencyFrom", "EUR")
                                .param("currencyTo", "USD")
                                .param("amount", "100")
                                .param("date", "2010-01-01")
                )
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user1@email.com", roles = "USER")
    public void negativeAmountTest() throws Exception {
        this.mockMvc
                .perform(
                        post("/convert")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("currencyFrom", "EUR")
                                .param("currencyTo", "USD")
                                .param("amount", "-5")
                                .param("date", "2010-01-01")
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user1@email.com", roles = "USER")
    public void emptyCurrencyFrom() throws Exception {
        this.mockMvc
                .perform(
                        post("/convert")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("currencyFrom", "")
                                .param("currencyTo", "USD")
                                .param("amount", "5")
                                .param("date", "2010-01-01")
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user1@email.com", roles = "USER")
    public void emptyCurrencyTo() throws Exception {
        this.mockMvc
                .perform(
                        post("/convert")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("currencyFrom", "EUR")
                                .param("currencyTo", "")
                                .param("amount", "5")
                                .param("date", "2010-01-01")
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk());
    }
}