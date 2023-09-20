package com.martindavidik.dataservice.controller;

import com.martindavidik.dataservice.domain.Card;
import com.martindavidik.dataservice.pojo.HashedPassword;
import com.martindavidik.dataservice.service.CardService;
import com.martindavidik.dataservice.service.SecurityService;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CardController {

    public final CardService cardService;
    public final SecurityService securityService;
    private static final int MAXIMUM_FAILED_PIN_ATTEMPTS = 3;

    public CardController(CardService cardService, SecurityService securityService) {
        this.cardService = cardService;
        this.securityService = securityService;
    }

    /**
     * Checks whether the payment card is valid and can be used
     *
     * @param cardNumber - number of payment card
     * @param expiryMonth - in format "mm"
     * @param expiryYear - in format "yy"
     * @param cvv - card verification value
     *
     * @return TRUE when card is VALID
     */
    @GetMapping("/verifyCard")
    public boolean verifyCard(
            @RequestParam @Size(min = 16, max = 19) String cardNumber,
            @RequestParam @Size(min = 2, max = 2) String expiryMonth,
            @RequestParam @Size(min = 2, max = 2) String expiryYear,
            @RequestParam @Size(min = 3, max = 4) String cvv
    ) {
        // checking if the card exists
        Optional<Card> card = cardService.getCardByData(cardNumber, expiryMonth, expiryYear, cvv);
        if (card.isEmpty())
        {
            return false;
        }

        // checking if the card is active
        if (!card.get().isActive())
        {
            return false;
        }

        return verifyCardForExpiration(card.get());
    }

    /**
     * Verifies whether the payment card is not expired
     *
     * @param card - payment card object
     *
     * @return TRUE when card is VALID (false when card is expired)
     */
    private boolean verifyCardForExpiration(Card card)
    {
        LocalDate today = LocalDate.now();
        String expirationDate = card.getExpiryMonth() + "/" + card.getExpiryYear();

        // parse credit card expiration date
        YearMonth ym = YearMonth.parse(expirationDate, DateTimeFormatter.ofPattern("MM/yy"));

        // get last day of month (taking care of leap years)
        LocalDate dateParsed = ym.atEndOfMonth();

        // expired if today is equals or after dateParsed
        return today.isBefore(dateParsed);
    }

    /**
     * Checks whether the PIN code corresponds to the PIN of the payment card
     *
     * @param cardNumber - number of payment card
     * @param expiryMonth - in format "mm"
     * @param expiryYear - in format "yy"
     * @param cvv - card verification value
     * @param pinCode - 4 digit pin code for the payment card
     *
     * @return TRUE when pin code is VALID
     */
    @GetMapping("/verifyPinCode")
    public boolean verifyPinCode(
            @RequestParam @Size(min = 16, max = 19) String cardNumber,
            @RequestParam @Size(min = 2, max = 2) String expiryMonth,
            @RequestParam @Size(min = 2, max = 2) String expiryYear,
            @RequestParam @Size(min = 3, max = 4) String cvv,
            @RequestParam @Size(min = 4, max = 4) String pinCode
    ) {
        Optional<Card> cardOptional = cardService.getCardByData(cardNumber, expiryMonth, expiryYear, cvv);
        if (cardOptional.isPresent()) {
            // get hashed password from database
            Card card = cardOptional.get();
            HashedPassword storedPassword = new HashedPassword(card.getPinCodeHash(), card.getPinCodeSalt());

            // pin is ok
            if (securityService.validatePassword(pinCode, storedPassword)) {
                card.setFailedPinAttempts(0); // reset counter of failed pin attempts
                cardService.save(card);

                return card.isActive();
            }

            // increase failed pin attempts after entering wrong pin code (after exceeding maximum attempts, account is disabled)
            card.setFailedPinAttempts(card.getFailedPinAttempts() + 1);
            if (card.getFailedPinAttempts() > MAXIMUM_FAILED_PIN_ATTEMPTS)
            {
                card.setActive(false);
            }
            cardService.save(card);
        }

        return false;
    }
}
