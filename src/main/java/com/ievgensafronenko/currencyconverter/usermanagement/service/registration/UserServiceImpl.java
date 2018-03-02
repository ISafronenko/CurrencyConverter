package com.ievgensafronenko.currencyconverter.usermanagement.service.registration;

import com.ievgensafronenko.currencyconverter.usermanagement.model.Role;
import com.ievgensafronenko.currencyconverter.usermanagement.model.User;
import com.ievgensafronenko.currencyconverter.usermanagement.model.UserRegistrationDto;
import com.ievgensafronenko.currencyconverter.usermanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Service for operations on User object.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Logger logger;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Method for loading user details by email.
     *
     * @param email - user email.
     * @return - user details
     * @throws UsernameNotFoundException in case user wasn't found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        logger.debug("Loading user by email: {}", email);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            logger.error("Can't load user by email: {}", email);
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        logger.debug("User successfully loaded: {}", user);

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /**
     * Method for finding user by email.
     *
     * @param email - user's email
     * @return user object.
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Method for saving user based on user user registration dto.
     *
     * @param userDTO - user registration dto.
     * @return saved user.
     */
    public User save(UserRegistrationDto userDTO) {

        logger.debug("Saving user: {}", userDTO);

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setDate(userDTO.getDob());
        user.setStreet(userDTO.getStreet());
        user.setCode(userDTO.getCode());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        User savedUser = userRepository.save(user);

        logger.debug("User successfully saved: {}", savedUser);

        return savedUser;
    }

    @Override
    public String loggedUserEmail() {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userEmail = "";
        if (user != null && user.getUsername() != null) {
            userEmail = user.getUsername();
        }

        logger.debug("Get user email of current user: ", userEmail);

        return userEmail;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}