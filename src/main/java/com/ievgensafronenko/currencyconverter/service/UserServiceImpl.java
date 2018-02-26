package com.ievgensafronenko.currencyconverter.service;

import com.ievgensafronenko.currencyconverter.model.Role;
import com.ievgensafronenko.currencyconverter.model.User;
import com.ievgensafronenko.currencyconverter.model.UserRegistrationDto;
import com.ievgensafronenko.currencyconverter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Service for operations on User object.
 */
@Service
public class UserServiceImpl implements UserService {

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
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

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
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}