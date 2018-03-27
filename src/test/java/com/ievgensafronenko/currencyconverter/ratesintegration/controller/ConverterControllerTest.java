package com.ievgensafronenko.currencyconverter.ratesintegration.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    @WithMockUser(username = "user1@email.com", roles = "USER")
    public void emptyDate() throws Exception {
        this.mockMvc
                .perform(
                        post("/convert")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("currencyFrom", "EUR")
                                .param("currencyTo", "")
                                .param("amount", "5")
                                .param("date", "")
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user1@email.com", roles = "USER")
    public void dateInFuture() throws Exception {
        this.mockMvc
                .perform(
                        post("/convert")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("currencyFrom", "EUR")
                                .param("currencyTo", "")
                                .param("amount", "5")
                                .param("date", "2199-01-01")
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk());
    }
}