package com.howwow.cpppaymentorchestratorservice.business.service;

import com.howwow.cpppaymentorchestratorservice.rest.dto.request.PaymentAuthorizationRequest;
import com.howwow.cpppaymentorchestratorservice.rest.dto.response.PaymentAuthorizationResponse;

public interface PaymentSagaService {
    PaymentAuthorizationResponse executePaymentSaga(PaymentAuthorizationRequest paymentAuthorizationRequest);

}
