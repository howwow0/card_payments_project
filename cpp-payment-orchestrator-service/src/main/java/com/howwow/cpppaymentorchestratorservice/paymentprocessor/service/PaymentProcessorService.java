package com.howwow.cpppaymentorchestratorservice.paymentprocessor.service;

import com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.response.PaymentProcessResponse;

public interface PaymentProcessorService {
    PaymentProcessResponse processPayment(PaymentProcessRequest request);
}
