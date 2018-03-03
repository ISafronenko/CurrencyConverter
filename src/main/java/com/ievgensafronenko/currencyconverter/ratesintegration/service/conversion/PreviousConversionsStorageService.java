package com.ievgensafronenko.currencyconverter.ratesintegration.service.conversion;

import com.ievgensafronenko.currencyconverter.ratesintegration.entities.PreviousConversions;
import com.ievgensafronenko.currencyconverter.ratesintegration.repository.PreviousConversionsRepository;
import com.ievgensafronenko.currencyconverter.usermanagement.service.registration.UserService;
import org.slf4j.Logger;
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
    private PreviousConversionsRepository repository;

    @Value("${previous.results.count}")
    private Integer size;

    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

    public void save(PreviousConversions data) {
        repository.save(data);
    }

    public List<PreviousConversions> findByUserEmailOrderByDateDesc() {

        String userEmail = userService.loggedUserEmail();
        logger.debug("Getting previous conversions data for user email: {}", userEmail);

        Pageable tenResults = new PageRequest(0, size);
        List<PreviousConversions> resultList = repository.findAllByUserEmailOrderByDateOfRequestDesc(userEmail, tenResults);

        if (resultList == null || resultList.size() == 0) {
            logger.debug("Can't load previous conversions for user email: {}", userEmail);
        }

        return resultList;
    }
}
