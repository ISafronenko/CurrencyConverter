package com.ievgensafronenko.currencyconverter.ratesintegration.service;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.repository.PreviousConversionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for operating with historical data.
 */
@Service
public class PreviousConversionsStorageService {

    @Autowired
    PreviousConversionsRepository repository;

    @Value("${previous.results.count}")
    private Integer size;

    public void save(PreviousConversions data) {
        repository.save(data);
    }

    public List<PreviousConversions> findByUserEmailOrderByDateDesc(String userEmail) {
        Pageable tenResults = new PageRequest(0, size);
        return repository.findAllByUserEmailOrderByDateDesc(userEmail, tenResults);
    }
}
