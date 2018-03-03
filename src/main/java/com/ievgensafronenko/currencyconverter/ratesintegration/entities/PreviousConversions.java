package com.ievgensafronenko.currencyconverter.ratesintegration.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for storing historical data of previous conversions.
 */
@Entity
@Table(name = "conversions")
public class PreviousConversions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userEmail;
    private String currencyFrom;
    private String currencyTo;
    private Double amount;
    private Double result;
    private Date dateOfRate;
    private Date dateOfRequest;

    public PreviousConversions() {
    }

    public PreviousConversions(String userEmail, String currencyFrom, String currencyTo, Double amount, Double result, Date dateOfRate, Date dateOfRequest) {
        this.userEmail = userEmail;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.result = result;
        this.dateOfRate = dateOfRate;
        this.dateOfRequest = dateOfRequest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Date getDateOfRate() {
        return dateOfRate;
    }

    public void setDateOfRate(Date dateOfRate) {
        this.dateOfRate = dateOfRate;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }
}
