package com.howwow.cpppaymentprocessorservice.business.service;

import com.howwow.cpppaymentprocessorservice.rest.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentprocessorservice.rest.dto.response.PaymentProcessResponse;

public interface PaymentProcessService {
    PaymentProcessResponse processPayment(PaymentProcessRequest paymentProcessRequest);
}
