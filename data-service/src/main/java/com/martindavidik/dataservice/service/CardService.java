package com.martindavidik.dataservice.service;

import com.martindavidik.dataservice.domain.Card;
import com.martindavidik.dataservice.dto.AccountInfoDTO;

import java.util.Optional;

public interface CardService {

    /**
     * Deducts money from the user's account
     *
     * @param card - payment card entity
     * @param amount - amount of money to be withdrawn
     *
     * @return TRUE when withdrawal was SUCCESSFUL
     */
    boolean withdrawMoney(Card card, double amount);

    /**
     * Returns object containing: account holder´s name, surname and account balance
     *
     * @param card - payment card entity
     *
     * @return AccountInfo data transfer object
     */
    AccountInfoDTO getCardHolderAccountInfo(Card card);

    /**
     * Sets PIN code for provided payment card
     *
     * @param card - card entity to be modified
     * @param pinCode - new PIN code for the credit card
     *
     * @return Card entity
     */
    Card setPinCodeForTheCard(Card card, String pinCode);

    /**
     * Returns Card entity from database by matching provided parameters
     *
     * @param cardNumber - number of payment card
     * @param expiryMonth - in format "mm"
     * @param expiryYear - in format "yy"
     * @param cvv - card verification value
     *
     * @return Optional<Card> entity
     */
    Optional<Card> getCardByData(String cardNumber, String expiryMonth, String expiryYear, String cvv);

    /**
     * Saves card entity to database
     *
     * @param card - entity to be saved
     *
     * @return Card entity
     */
    Card save(Card card);

    /**
     * Deletes card entity from database
     *
     * @param card - entity to be deleted
     */
    void delete(Card card);
}
