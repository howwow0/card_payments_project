package com.howwow.cppbankgateway.business.impl;

import com.howwow.cppbankgateway.business.BankService;
import com.howwow.cppbankgateway.rest.dto.request.GatewayAuthorizationRequest;
import com.howwow.cppbankgateway.rest.dto.response.GatewayAuthorizationResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BankServiceImpl implements BankService {

    @Override
    public GatewayAuthorizationResponse authorize(GatewayAuthorizationRequest request) {
        if (request.amount().compareTo(BigDecimal.valueOf(10000)) > 0)
            return GatewayAuthorizationResponse.rejected(UUID.randomUUID(), "Недостаточно денег на карте");
        return GatewayAuthorizationResponse.approved(UUID.randomUUID(), "Транзакция обработана банком - эмитентом");
    }

}
