package com.ievgensafronenko.currencyconverter.ratesintegration.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for storing historical data of previous conversions.
 */
@Entity
@Table(name = "conversions")

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "id")
public class PreviousConversions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String userEmail;
    @NonNull
    private String currencyFrom;
    @NonNull
    private String currencyTo;
    @NonNull
    private Double amount;
    @NonNull
    private Double result;
    @NonNull
    private Date dateOfRate;
    @NonNull
    private Date dateOfRequest;
}
