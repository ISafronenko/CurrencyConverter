package com.ievgensafronenko.currencyconverter.usermanagement.controller;

import com.ievgensafronenko.currencyconverter.usermanagement.model.User;
import com.ievgensafronenko.currencyconverter.usermanagement.model.UserRegistrationDto;
import com.ievgensafronenko.currencyconverter.usermanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller which handles user registration.
 */
@Controller
@RequestMapping("/registration")
@Api(value = "User registration resource", description = "This is API for user registration.")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

    @ApiOperation(value = "Endpoint for returning user registration form.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid request format"),
                    @ApiResponse(code = 500, message = "Temporary technical server error")
            }
    )
    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @ApiOperation(value = "Endpoint for registering user.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid request format"),
                    @ApiResponse(code = 500, message = "Temporary technical server error")
            }
    )
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result, Model model) {

        String userEmail = userDto.getEmail();
        logger.debug("Starting registering user account for user email: {}", userEmail);

        User existing = userService.findByEmail(userEmail);
        if (existing != null) {
            logger.error("User with email: {} are already exists", userEmail);
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userDto);
        logger.debug("User account successfully created {}", userDto);
        logger.debug("Redirecting to login.");
        return "redirect:/login";
    }
}
