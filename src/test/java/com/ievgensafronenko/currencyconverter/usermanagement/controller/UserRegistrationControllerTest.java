package com.ievgensafronenko.currencyconverter.usermanagement.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.ievgensafronenko.currencyconverter.common.UserDataHelper.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitRegistrationSuccess() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", "1986-10-10")
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", "on")
                )
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl(REGISTRATION_SUCCESS_REDIRECT))
                .andExpect(status().is3xxRedirection());
    }
}
