package com.ievgensafronenko.currencyconverter.ratesintegration.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Class represent data transfer object for getting data from currency convert form.
 */
public class ConvertDTO {

    @NotEmpty
    private String currencyFrom;
    @NotEmpty
    private String currencyTo;
    @NotNull
    private Double amount;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;

    public ConvertDTO() {
    }

    public ConvertDTO(String currencyFrom, String currencyTo, Double amount, Date date) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.date = date;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ConvertDTO{" +
                "currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
