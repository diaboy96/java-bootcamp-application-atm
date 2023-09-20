package com.martindavidik.dataservice.service;

import com.martindavidik.dataservice.domain.Card;

import java.util.Optional;

public interface CardService {

    Optional<Card> getCardByData(String cardNumber, String expiryMonth, String expiryYear, String cvv);

    Card save(Card card);

    void delete(Card card);
}
