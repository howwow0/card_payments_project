package com.howwow.carddecryptionstarter.dto;

public record CardDataDto(
        String cardNumber,
        String expiryDate,
        String cvv
) {
}
