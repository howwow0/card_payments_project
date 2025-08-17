package com.howwow.cpppaymentgatewayservice.client.dto.request;

public record CardAuthRequest(
        String cardNumber,
        String expiryDate,
        String cvv
) {
}