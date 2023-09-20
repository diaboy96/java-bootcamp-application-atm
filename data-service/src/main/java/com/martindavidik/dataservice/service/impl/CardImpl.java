package com.martindavidik.dataservice.service.impl;

import com.martindavidik.dataservice.domain.Card;
import com.martindavidik.dataservice.repository.CardRepository;
import com.martindavidik.dataservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Optional<Card> getCardByData(String cardNumber, String expiryMonth, String expiryYear, String cvv) {
        return cardRepository.findCardByCardNumberAndExpiryMonthAndExpiryYearAndCvv(cardNumber, expiryMonth, expiryYear, cvv);
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void delete(Card card) {
        cardRepository.delete(card);
    }
}
