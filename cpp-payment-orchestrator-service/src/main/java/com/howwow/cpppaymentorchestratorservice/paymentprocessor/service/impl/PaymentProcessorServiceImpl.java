package com.howwow.cpppaymentorchestratorservice.paymentprocessor.service.impl;

import com.howwow.cpppaymentorchestratorservice.bankgateway.exception.PaymentAuthorizationException;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.client.PaymentProcessorClient;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.response.PaymentProcessResponse;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.exception.PaymentProcessException;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.service.PaymentProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentProcessorServiceImpl implements PaymentProcessorService {

    private final PaymentProcessorClient paymentProcessorClient;

    @Override
    public PaymentProcessResponse processPayment(PaymentProcessRequest request) {
        try {
            return paymentProcessorClient.processPayment(request);
        } catch (Exception e) {
            throw new PaymentAuthorizationException(
                    "Ошибка сервиса завершения платежа: " + e.getMessage(),
                    HttpStatus.BAD_GATEWAY
            );
        }
    }
}
