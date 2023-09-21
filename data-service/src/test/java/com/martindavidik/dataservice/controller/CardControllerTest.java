package com.martindavidik.dataservice.controller;

import com.martindavidik.dataservice.service.CardService;
import com.martindavidik.dataservice.service.SecurityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private static CardService cardService;

    @MockBean
    private static SecurityService securityService;

    private static final String CARD_NUMBER = "4024007146695589";
    private static final String EXPIRY_MONTH = "02";
    private static final String EXPIRY_YEAR = "26";
    private static final String CVV = "124";
    private static final String PIN_CODE = "0123";

    @Test
    public void testCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    public void testVerifyCard() throws Exception {
        // correct request
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/verifyCard")
                        .param("cardNumber", CARD_NUMBER)
                        .param("expiryMonth", EXPIRY_MONTH)
                        .param("expiryYear", EXPIRY_YEAR)
                        .param("cvv", CVV)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        // incorrect request (missing parameter)
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/verifyCard")
                        .param("cardNumber", CARD_NUMBER)
                        .param("expiryYear", EXPIRY_YEAR)
                        .param("cvv", CVV)
                )
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testVerifyPinCode() throws Exception {
        // correct request
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/verifyPinCode")
                        .param("cardNumber", CARD_NUMBER)
                        .param("expiryMonth", EXPIRY_MONTH)
                        .param("expiryYear", EXPIRY_YEAR)
                        .param("cvv", CVV)
                        .param("pinCode", PIN_CODE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        // incorrect request (missing pin code request param)
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/verifyPinCode")
                        .param("cardNumber", CARD_NUMBER)
                        .param("expiryMonth", EXPIRY_MONTH)
                        .param("expiryYear", EXPIRY_YEAR)
                        .param("cvv", CVV)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
