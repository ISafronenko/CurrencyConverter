package com.ievgensafronenko.currencyconverter.usermanagement.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * JPA Entity for persisting User related information.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "password")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String code;
    private String city;
    private String street;
    private String country;
    private Date date;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
