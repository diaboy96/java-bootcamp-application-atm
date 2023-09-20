package com.martindavidik.dataservice.controller;

import com.martindavidik.dataservice.domain.Card;
import com.martindavidik.dataservice.service.CardService;
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

    public CardController(CardService cardService) {
        this.cardService = cardService;
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
}