package com.howwow.cpppaymentorchestratorservice.bankgateway.service;

import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.response.PaymentAuthorizationGatewayResponse;

public interface BankGatewayService {
    PaymentAuthorizationGatewayResponse authorize(PaymentAuthorizationGatewayRequest request);
}
