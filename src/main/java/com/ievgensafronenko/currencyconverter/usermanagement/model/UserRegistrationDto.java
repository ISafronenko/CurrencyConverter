package com.ievgensafronenko.currencyconverter.usermanagement.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * User registration data transfer object.
 */
public class UserRegistrationDto {

    @Size(min = 2, max = 15)
    @NotEmpty
    private String firstName;

    @Size(min = 2, max = 15)
    @NotEmpty
    private String lastName;

    @Size(min = 2, max = 8)
    @NotEmpty
    private String code;

    @Size(min = 2, max = 50)
    @NotEmpty
    private String city;

    @Size(min = 2, max = 50)
    @NotEmpty
    private String street;

    @Size(min = 2, max = 30)
    @NotEmpty
    private String country;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date dob;

    @NotEmpty
    @Size(min = 5, max = 10)
    private String password;

    @Size(min = 5, max = 10)
    @NotEmpty
    private String confirmPassword;

    @Email
    @NotEmpty
    @Size(min = 5, max = 30)
    private String email;

    @Email
    @NotEmpty
    @Size(min = 5, max = 30)
    private String confirmEmail;

    @AssertTrue
    private Boolean terms;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", code='" + code + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", country='" + country + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", confirmEmail='" + confirmEmail + '\'' +
                ", terms=" + terms +
                '}';
    }
}
