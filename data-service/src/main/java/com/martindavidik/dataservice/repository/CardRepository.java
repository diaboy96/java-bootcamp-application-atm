package com.martindavidik.dataservice.repository;

import com.martindavidik.dataservice.domain.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Integer> {

    Optional<Card> findCardByCardNumberAndExpiryMonthAndExpiryYearAndCvv(
            String cardNumber,
            String expiryMonth,
            String expiryYear,
            String cvv
    );
}
