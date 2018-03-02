package com.ievgensafronenko.currencyconverter.ratesintegration.model;

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
    private Date date;

    public PreviousConversions() {
    }

    public PreviousConversions(String userEmail, String currencyFrom, String currencyTo, Double amount, Double result, Date date) {
        this.userEmail = userEmail;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.result = result;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PreviousConversions{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", amount=" + amount +
                ", result=" + result +
                ", date=" + date +
                '}';
    }
}
