package com.howwow.carddecryptionstarter.carddecrypt.dto;

public record CardDataDto(
        String cardNumber,
        String expiryDate,
        String cvv
) {
}
