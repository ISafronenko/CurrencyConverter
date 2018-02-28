package com.ievgensafronenko.currencyconverter.ratesintegration.service;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.repository.PreviousConversionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for operating with historical data.
 */
@Service
public class PreviousConversionsStorageService {

    @Autowired
    PreviousConversionsRepository repository;

    public void save(PreviousConversions data) {
        repository.save(data);
    }

    public List<PreviousConversions> findByUserEmailOrderByDateDesc(String userEmail) {
        return repository.findAllByUserEmailOrderByDateDesc(userEmail);
    }
}
