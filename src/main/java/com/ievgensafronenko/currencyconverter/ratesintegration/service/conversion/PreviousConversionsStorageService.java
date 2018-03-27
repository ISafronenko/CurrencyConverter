package com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion;

import com.ievgensafronenko.currencyconverter.ratesintegration.entities.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.repository.PreviousConversionsRepository;
import com.ievgensafronenko.currencyconverter.usermanagement.service.registration.UserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PreviousConversionsStorageService {

    @Autowired
    private PreviousConversionsRepository repository;

    @Autowired
    private UserService userService;

    @Value("${previous.results.count}")
    private Integer size;

    public void save(PreviousConversions data) {
        repository.save(data);
    }

    public List<PreviousConversions> findByUserEmailOrderByDateDesc() {

        String userEmail = userService.loggedUserEmail();
        log.debug("Getting previous conversions data for user email: {}", userEmail);

        Pageable tenResults = new PageRequest(0, size);
        List<PreviousConversions> resultList = repository.findAllByUserEmailOrderByDateOfRequestDesc(userEmail, tenResults);

        if (resultList == null || resultList.size() == 0) {
            log.error("Can't load previous conversions for user email: {}", userEmail);
        }

        return resultList;
    }
}
