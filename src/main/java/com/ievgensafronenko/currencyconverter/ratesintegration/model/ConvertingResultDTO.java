package com.ievgensafronenko.currencyconverter.ratesintegration.model;

/**
 * Class represent results of currency converting.
 */
public class ConvertingResultDTO {

    private String currencyFrom;
    private String currencyTo;
    private Double amount;
    private Double result;

    public ConvertingResultDTO(String currencyFrom, String currencyTo, Double amount, Double result) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.result = result;
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
}
