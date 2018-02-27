package com.ievgensafronenko.currencyconverter.ratesintegration.repository;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.HistoryData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for persisting historical data.
 */
public interface HistoryRepository extends JpaRepository<HistoryData, Long> {
    HistoryData findByUserEmail(String email);
}
