package com.ievgensafronenko.currencyconverter.usermanagement.service;

import com.ievgensafronenko.currencyconverter.usermanagement.model.User;
import com.ievgensafronenko.currencyconverter.usermanagement.model.UserRegistrationDto;
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
}