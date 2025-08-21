package com.howwow.cpppaymentorchestratorservice.bankgateway.service.impl;

import com.howwow.cpppaymentorchestratorservice.bankgateway.client.BankGatewayClient;
import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cpppaymentorchestratorservice.bankgateway.exception.PaymentAuthorizationException;
import com.howwow.cpppaymentorchestratorservice.bankgateway.service.BankGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankGatewayServiceImpl implements BankGatewayService {
    private final BankGatewayClient bankGatewayClient;

    @Override
    public PaymentAuthorizationGatewayResponse authorize(PaymentAuthorizationGatewayRequest request) {
        try {
            return bankGatewayClient.authorizePayment(request);
        } catch (Exception e) {
            throw new PaymentAuthorizationException(
                    "Ошибка банковского шлюза: " + e.getMessage(),
                    HttpStatus.BAD_GATEWAY
            );
        }
    }
}
