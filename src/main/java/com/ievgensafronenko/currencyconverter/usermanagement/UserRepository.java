package com.ievgensafronenko.currencyconverter.usermanagement;

import com.ievgensafronenko.currencyconverter.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operation on User Entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}