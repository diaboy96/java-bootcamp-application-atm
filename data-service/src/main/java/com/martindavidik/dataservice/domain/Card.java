package com.martindavidik.dataservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Card {

    @Id
    @Column(unique = true)
    private String cardNumber;

    @Size(min = 2, max = 2)
    private String expiryMonth;

    @Size(min = 2, max = 2)
    private String expiryYear;

    @Size(min = 3, max = 4)
    private String cvv;

    private String pinCodeHash;

    private int failedPinAttempts = 0;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Card card = (Card) o;
        return getCardNumber() != null && Objects.equals(getCardNumber(), card.getCardNumber());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
