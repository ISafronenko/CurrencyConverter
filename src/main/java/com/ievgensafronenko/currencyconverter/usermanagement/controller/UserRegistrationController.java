package com.ievgensafronenko.currencyconverter.usermanagement.controller;

import com.ievgensafronenko.currencyconverter.usermanagement.dto.UserRegistrationDto;
import com.ievgensafronenko.currencyconverter.usermanagement.service.registration.UserService;
import com.ievgensafronenko.currencyconverter.usermanagement.service.validation.UserRegistrationValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Controller which handles user registration.
 */
@Controller
@RequestMapping("/registration")
@Api(value = "User registration resource", description = "This is API for user registration.")
@Slf4j
public class UserRegistrationController {

    private UserRegistrationValidationService validationService;
    private UserService userService;

    @Autowired
    public UserRegistrationController(UserRegistrationValidationService validationService,
                                      UserService userService) {
        this.validationService = validationService;
        this.userService = userService;
    }

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
                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors() || validationService.validate(userDto, bindingResult)) {
            log.error("Validation has failed for user {}", userDto.getEmail());
            return "registration";
        }

        userService.save(userDto);
        log.debug("User account successfully created {}", userDto);
        return "redirect:/login";
    }
}
