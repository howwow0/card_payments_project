package com.howwow.carddecryptionstarter.carddecrypt.dto;

public record DecryptedCardData(
        String cardNumber,
        String expiryDate,
        String cvv
) {
}
