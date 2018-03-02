package com.ievgensafronenko.currencyconverter.usermanagement.service.registration;

import com.ievgensafronenko.currencyconverter.usermanagement.entities.User;
import com.ievgensafronenko.currencyconverter.usermanagement.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * This interface defines user service type and behaviour.
 */
public interface UserService extends UserDetailsService {

    /**
     * Method for finding user by email.
     *
     * @param email - user's email
     * @return - user object.
     */
    User findByEmail(String email);

    /**
     * Method for storing user object based on user registration data transfer object.
     *
     * @param userDTO - user registration data transfer object
     * @return - stored user object.
     */
    User save(UserRegistrationDto userDTO);

    /**
     * Method for getting logged in user email.
     *
     * @return - string with user email.
     */
    String loggedUserEmail();
}