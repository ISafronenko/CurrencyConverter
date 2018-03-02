package com.ievgensafronenko.currencyconverter.usermanagement.repository;

import com.ievgensafronenko.currencyconverter.usermanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operation on User Entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}