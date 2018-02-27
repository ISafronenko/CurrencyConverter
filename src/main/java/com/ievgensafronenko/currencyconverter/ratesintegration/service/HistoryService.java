package com.ievgensafronenko.currencyconverter.ratesintegration.service;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.HistoryData;
import com.ievgensafronenko.currencyconverter.ratesintegration.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for operating with historical data.
 */
@Service
public class HistoryService {

    @Autowired
    HistoryRepository repository;

    public void save(HistoryData data){
        repository.save(data);
    }

    public List<HistoryData> findAll(){
        return repository.findAll();
    }
}
