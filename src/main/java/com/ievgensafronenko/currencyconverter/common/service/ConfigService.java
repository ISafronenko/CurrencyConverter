package com.ievgensafronenko.currencyconverter.common.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Centralized config service for handling properties.
 */
@Service
@Getter
public class ConfigService {

    @Value("#{'${currencies}'.split(',')}")
    private List<String> currencies;

    @Value("#{'${countries}'.split(',')}")
    private List<String> countries;

    @Value("${previous.results.count}")
    private Integer previousResultsCount;

    @Value("${rate.service.url}")
    private String latestRateUrl;

    @Value("${rate.service.url.historical}")
    private String historicalRateUrl;

    @Value("${latest.date}")
    private Date latestDate;

    @Value("${date.format}")
    private String dateFormat;
}
