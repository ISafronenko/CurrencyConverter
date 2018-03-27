package com.ievgensafronenko.currencyconverter.usermanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * User registration data transfer object.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"password", "confirmPassword"})
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
}
