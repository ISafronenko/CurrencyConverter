package com.ievgensafronenko.currencyconverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationTest {

    private static final String FIRST_NAME = "user 2";
    private static final String LAST_NAME = "user 2";
    private static final String EMAIL = "user2@email.com";
    private static final String PASSWORD = "password";
    private static final String REGISTRATION_SUCCESS_REDIRECT = "/login";
    private static final String CODE = "81673";
    private static final String CITY = "Berlin";
    private static final String COUNTRY = "Germany";

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
                                .param("street", "Unter den Linden")
                                .param("country", COUNTRY)
                                .param("dob", "1986-10-10")
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", "on")
                )
                .andExpect(redirectedUrl(REGISTRATION_SUCCESS_REDIRECT))
                .andExpect(status().is3xxRedirection());
    }
}
