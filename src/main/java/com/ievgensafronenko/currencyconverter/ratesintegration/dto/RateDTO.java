package com.ievgensafronenko.currencyconverter.ratesintegration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * Class for deserializing json from rates service to Java POJO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RateDTO {

    @JsonProperty("rates")
    private Map<String, Double> rates;
}
