package com.ievgensafronenko.currencyconverter.usermanagement.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.ievgensafronenko.currencyconverter.common.UserDataHelper.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RegistrationFormTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitRegistrationWithoutName() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithShortName() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME_SHORT)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithLongName() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME_LONG)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"));
    }

    //================================== Last Name Tests =====================================//

    @Test
    public void submitRegistrationWithoutLastName() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithEmptyLastName() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME_LONG)
                                .param("lastName", "")
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithShortLastName() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", FIRST_NAME_SHORT)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithLongLastName() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME_LONG)
                                .param("lastName", FIRST_NAME_LONG)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    //================================== User Data of Birth =====================================//
    @Test
    public void submitRegistrationWithDOBInFuture() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", "2099-01-01")
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }
    //================================== User Street Tests =====================================//

    @Test
    public void submitRegistrationWithoutStreet() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithEmptyStreet() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", "")
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithShortStreet() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", "s")
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithLongStreet() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", "asdnjnvjfnvnnnjn sdkfnsdjfnjsdnfdnsjfnsd dsjfnsdfnsdjf")
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    //================================== User ZIP Code Tests =====================================//
    @Test
    public void submitRegistrationWithoutZipCode() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithEmptyCode() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", "")
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithShortZipCode() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", "a")
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithLongZipCode() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", "888-89888")
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    //================================== User Country Tests =====================================//
    @Test
    public void submitRegistrationWithoutCountry() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithEmptyCountry() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", "")
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithShortCountry() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", "a")
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithLongCountry() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country","This is really long country name")
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    //================================== User Email Tests =====================================//

    @Test
    public void submitRegistrationWithIncorrectEmail() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", "email")
                                .param("email", "email")
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithNotMatchingEmail() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("email", "email2@gmail.com")
                                .param("confirmEmail", "email@gmail.com")
                                .param("password", PASSWORD)
                                .param("confirmPassword", PASSWORD)
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    //================================== User Password Tests =====================================//

    @Test
    public void submitRegistrationWithIncorrectPassword() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", EMAIL)
                                .param("email", EMAIL)
                                .param("password", "12")
                                .param("confirmPassword", "12")
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }

    @Test
    public void submitRegistrationWithNotMatchingPasswords() throws Exception {
        this.mockMvc
                .perform(
                        post(REGISTRATION)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", FIRST_NAME)
                                .param("lastName", LAST_NAME)
                                .param("code", CODE)
                                .param("city", CITY)
                                .param("street", STREET)
                                .param("country", COUNTRY)
                                .param("dob", DATE_OF_BIRTH)
                                .param("confirmEmail", "email@gmail.com")
                                .param("email", "email@gmail.com")
                                .param("password", "qwertyty")
                                .param("confirmPassword", "asdfghyr")
                                .param("terms", TERMS_ON)
                )
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("user"));
    }
}
