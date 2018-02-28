package com.ievgensafronenko.currencyconverter.ratesintegration.repository;

import com.ievgensafronenko.currencyconverter.ratesintegration.model.PreviousConversions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for persisting historical data.
 */
public interface PreviousConversionsRepository extends JpaRepository<PreviousConversions, Long> {
    List<PreviousConversions> findAllByUserEmailOrderByDateDesc(String email);
}
