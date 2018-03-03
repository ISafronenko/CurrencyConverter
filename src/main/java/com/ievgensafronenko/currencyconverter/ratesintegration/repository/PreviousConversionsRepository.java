package com.ievgensafronenko.currencyconverter.ratesintegration.repository;

import com.ievgensafronenko.currencyconverter.ratesintegration.entities.PreviousConversions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for persisting historical data.
 */
@Repository
public interface PreviousConversionsRepository extends JpaRepository<PreviousConversions, Long> {
    List<PreviousConversions> findAllByUserEmailOrderByDateOfRequestDesc(String email, Pageable pageable);
}
