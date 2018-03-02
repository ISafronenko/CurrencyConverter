package com.ievgensafronenko.currencyconverter.usermanagement.service.validation;

import com.ievgensafronenko.currencyconverter.usermanagement.model.User;
import com.ievgensafronenko.currencyconverter.usermanagement.model.UserRegistrationDto;
import com.ievgensafronenko.currencyconverter.usermanagement.service.registration.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Objects;

/**
 * Service for user data input validation.
 */
@Service
public class UserRegistrationValidationService {

    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

    /**
     * Method for user registration form validation.
     *
     * @param userDto - object which contains user info.
     * @param result  - binding result.
     * @return true if validation has failed, false otherwise.
     */
    public boolean validate(UserRegistrationDto userDto, BindingResult result) {

        logger.debug("Validating input before registering user.");
        boolean isValidationFailed = false;

        if (isEmailConfirmationCorrect(userDto, result)
                || isPasswordConfirmationCorrect(userDto, result)
                || isUserAlreadyExists(userDto, result)) {
            isValidationFailed = true;
        }

        logger.debug("Is validation failed: {}", isValidationFailed);
        return isValidationFailed;
    }

    /**
     * Method for validation is user with specified email already exists.
     *
     * @param userDto       user dto.
     * @param bindingResult binding result.
     * @return true if validation has failed, false otherwise.
     */
    private boolean isUserAlreadyExists(UserRegistrationDto userDto, BindingResult bindingResult) {
        String userEmail = userDto.getEmail();
        logger.debug("Starting registering user account for user email: {}", userEmail);

        User existing = userService.findByEmail(userEmail);

        if (existing != null) {
            logger.error("User with email: {} are already exists", userEmail);
            bindingResult.rejectValue("email", null, "There is already an account registered with that email");
            return true;
        }

        return false;
    }

    /**
     * Method for validation are email and confirmation email the same.
     *
     * @param userDto       user dto.
     * @param bindingResult binding result.
     * @return true if validation has failed, false otherwise.
     */
    private boolean isEmailConfirmationCorrect(UserRegistrationDto userDto, BindingResult bindingResult) {
        String email = userDto.getEmail();
        String confirmEmail = userDto.getConfirmEmail();
        boolean confirmationDataCorrect = isComfirmationDataCorrect(email, confirmEmail);

        if (confirmationDataCorrect) {
            bindingResult.rejectValue("email", null, "Email das not match confirmation email.");
        }

        return confirmationDataCorrect;
    }

    /**
     * Method for validation are password and confirmation password the same.
     *
     * @param userDto       user dto.
     * @param bindingResult binding result.
     * @return true if validation has failed, false otherwise.
     */
    private boolean isPasswordConfirmationCorrect(UserRegistrationDto userDto, BindingResult bindingResult) {
        String password = userDto.getPassword();
        String confirmPassword = userDto.getConfirmPassword();
        boolean confirmationDataCorrect = isComfirmationDataCorrect(password, confirmPassword);

        if (confirmationDataCorrect) {
            bindingResult.rejectValue("password", null, "Password das not match confirmation password.");
        }

        return confirmationDataCorrect;
    }

    private boolean isComfirmationDataCorrect(String param, String confirmParam) {
        return !Objects.equals(param, confirmParam);
    }
}
