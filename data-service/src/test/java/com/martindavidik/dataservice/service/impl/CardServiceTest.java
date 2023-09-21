package com.martindavidik.dataservice.service.impl;

import com.martindavidik.dataservice.domain.Card;
import com.martindavidik.dataservice.service.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("test")
public class CardServiceTest {

    @Autowired
    CardService cardService;

    private static final String CARD_NUMBER = "4024007146695589";
    private static final String EXPIRY_MONTH = "02";
    private static final String EXPIRY_YEAR = "26";
    private static final String CVV = "124";

    @Test
    public void givenCardAndWithdrawalAmount_whenWithdrawMoney_ThenAccountBalanceDecreases() {
        // Given
        double withdrawalAmount = 144.58;
        double withdrawalAmountLackOfFunds = 1449999999.58;

        Optional<Card> adamsCard = cardService.getCardByData(CARD_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR, CVV);
        if (adamsCard.isPresent()) {
            // When
            Card card = adamsCard.get();

            // Then
            assertThat(cardService.withdrawMoney(card, withdrawalAmount)).isTrue();
            assertThat(cardService.withdrawMoney(card, withdrawalAmountLackOfFunds)).isFalse();
        } else {
            fail("Data was not found in database");
        }
    }

    @Test
    public void givenCardData_whenGetCardByData_ThenDataFromDatabaseMatchGivenData() {
        // When
        Optional<Card> adamsCardOptional = cardService.getCardByData(CARD_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR, CVV);
        if (adamsCardOptional.isPresent()) {
            Card card = adamsCardOptional.get();

            // Then
            assertThat(card.getCardNumber()).isEqualTo(CARD_NUMBER);
            assertThat(card.getExpiryMonth()).isEqualTo(EXPIRY_MONTH);
            assertThat(card.getExpiryYear()).isEqualTo(EXPIRY_YEAR);
            assertThat(card.getCvv()).isEqualTo(CVV);
        } else {
            fail("Data was not found in database");
        }
    }
}
