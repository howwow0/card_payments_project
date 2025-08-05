package com.howwow.cppcardgateway.client.dto.request;

public record CardAuthRequest(
        String cardNumber,
        String expiryDate,
        String cvv
) {
}