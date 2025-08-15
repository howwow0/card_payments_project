package com.howwow.cppauthrequest.bankgateway.service;

import com.howwow.cppauthrequest.bankgateway.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cppauthrequest.bankgateway.dto.response.PaymentAuthorizationGatewayResponse;

public interface BankGatewayService {
    PaymentAuthorizationGatewayResponse authorize(PaymentAuthorizationGatewayRequest request);
}
