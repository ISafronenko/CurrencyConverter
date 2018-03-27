package com.ievgensafronenko.currencyconverter.ratesintegration.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import java.util.Date;

/**
 * Class represent data transfer object for getting data from currency convert form.
 */
@Data
public class ConvertDTO {
    @NotEmpty
    private String currencyFrom;

    @NotEmpty
    private String currencyTo;

    @NotNull
    private Double amount;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date date;

    public ConvertDTO() {
    }
}
