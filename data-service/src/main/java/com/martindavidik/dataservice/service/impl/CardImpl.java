package com.martindavidik.dataservice.service.impl;

import com.martindavidik.dataservice.domain.Card;
import com.martindavidik.dataservice.repository.CardRepository;
import com.martindavidik.dataservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void delete(Card card) {
        cardRepository.delete(card);
    }
}
