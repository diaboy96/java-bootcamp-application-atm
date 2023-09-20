package com.martindavidik.dataservice.service;

import com.martindavidik.dataservice.domain.Card;

public interface CardService {

    Card save(Card card);

    void delete(Card card);
}
