package com.howwow.cppcarddecryptionstarter.carddecrypt.dto;

public record DecryptedCardData(
        String cardNumber,
        String expiryDate,
        String cvv
) {
}
