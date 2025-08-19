package com.howwow.cppbankgatewayservice.business.impl;

import com.howwow.cppbankgatewayservice.business.BankService;
import com.howwow.cppbankgatewayservice.rest.dto.request.GatewayAuthorizationRequest;
import com.howwow.cppbankgatewayservice.rest.dto.response.GatewayAuthorizationResponse;
import com.howwow.cppcarddecryptionstarter.carddecrypt.dto.DecryptedCardData;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BankServiceImpl implements BankService {

    @Override
    public GatewayAuthorizationResponse authorize(GatewayAuthorizationRequest request, DecryptedCardData decryptedCardData) {

        String cardNumber = decryptedCardData.cardNumber();
        String maskedCard = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        if (request.amount().compareTo(BigDecimal.valueOf(10000)) > 0) {
            return GatewayAuthorizationResponse.rejected(
                    UUID.randomUUID(),
                    "Недостаточно денег на карте " + maskedCard
            );
        }

        return GatewayAuthorizationResponse.approved(
                UUID.randomUUID(),
                "Транзакция обработана банком-эмитентом для карты " + maskedCard
        );
    }

}
