package com.howwow.cppbankgatewayservice.business.impl;

import com.howwow.cppbankgatewayservice.rest.dto.request.GatewayAuthorizationRequest;
import com.howwow.cppbankgatewayservice.rest.dto.response.GatewayAuthorizationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceImplTest {

    private BankServiceImpl bankServiceImpl;

    @BeforeEach
    void setUp() {
        bankServiceImpl = new BankServiceImpl();
    }

    @Test
    void authorize_shouldApproveWhenAmountLessThanOrEqual10000() {
        GatewayAuthorizationRequest request = new GatewayAuthorizationRequest(
                BigDecimal.valueOf(9999.99),
                Currency.getInstance("RUB"),
                "4111111111111111",
                "12/25",
                "123"
        );

        GatewayAuthorizationResponse response = bankServiceImpl.authorize(request);

        assertTrue(response.isApproved());
        assertEquals("Транзакция обработана банком - эмитентом", response.reason());
        assertNotNull(response.bankTransactionId());
    }

    @Test
    void authorize_shouldRejectWhenAmountGreaterThan10000() {
        // Arrange
        GatewayAuthorizationRequest request = new GatewayAuthorizationRequest(
                BigDecimal.valueOf(10000.1),
                Currency.getInstance("RUB"),
                "4111111111111111",
                "12/25",
                "123"
        );

        GatewayAuthorizationResponse response = bankServiceImpl.authorize(request);

        assertFalse(response.isApproved());
        assertEquals("Недостаточно денег на карте", response.reason());
        assertNotNull(response.bankTransactionId());
    }
}