package com.ievgensafronenko.currencyconverter.ratesintegration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Class for deserializing json from rates service to Java POJO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    @JsonProperty("base")
    private String base;
    @JsonProperty("rates")
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "base='" + base + '\'' +
                ", rates=" + rates +
                '}';
    }
}
