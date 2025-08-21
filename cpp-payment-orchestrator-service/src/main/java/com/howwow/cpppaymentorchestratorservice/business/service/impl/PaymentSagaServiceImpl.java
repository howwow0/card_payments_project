package com.howwow.cpppaymentorchestratorservice.business.service.impl;

import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cpppaymentorchestratorservice.bankgateway.service.BankGatewayService;
import com.howwow.cpppaymentorchestratorservice.business.service.PaymentSagaService;
import com.howwow.cpppaymentorchestratorservice.logging.dto.request.CreateLogRequest;
import com.howwow.cpppaymentorchestratorservice.logging.service.LoggingService;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.response.PaymentProcessResponse;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.service.PaymentProcessorService;
import com.howwow.cpppaymentorchestratorservice.rest.dto.request.PaymentAuthorizationRequest;
import com.howwow.cpppaymentorchestratorservice.rest.dto.response.PaymentAuthorizationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentSagaServiceImpl implements PaymentSagaService {
    private final BankGatewayService bankGatewayService;
    private final PaymentProcessorService paymentProcessorService;
    private final LoggingService loggingService;

    @Override
    public PaymentAuthorizationResponse executePaymentSaga(PaymentAuthorizationRequest paymentAuthorizationRequest) {
        UUID transactionId = UUID.randomUUID();

        log.info("Запуск саги для платежа: {}", transactionId);

        log.info("Шаг 1: Авторизация в банке");
        var authRequest = new PaymentAuthorizationGatewayRequest(
                paymentAuthorizationRequest.amount(),
                paymentAuthorizationRequest.currency()
        );
        PaymentAuthorizationGatewayResponse authResponse = bankGatewayService.authorize(authRequest);

        log.info("Шаг 2: Обработка платежа");
        var processRequest = new PaymentProcessRequest(
                paymentAuthorizationRequest.amount(),
                paymentAuthorizationRequest.currency(),
                paymentAuthorizationRequest.merchantId(),
                paymentAuthorizationRequest.email(),
                transactionId,
                authResponse.bankTransactionId(),
                authResponse.isApproved(),
                authResponse.reason()
        );
        PaymentProcessResponse processResponse = paymentProcessorService.processPayment(processRequest);

        log.info("Шаг 3: Логирование операции");
        var logRequest = new CreateLogRequest(
                LocalDateTime.now(),
                LogLevel.INFO,
                "cpp-payment-orchestrator-service",
                String.format("Платеж %s успешно обработан.",
                        processResponse.transactionId()),
                processResponse.transactionId().toString()
        );
        loggingService.sendLog(logRequest);

        return new PaymentAuthorizationResponse(
                processResponse.transactionId(),
                processResponse.bankTransactionId(),
                processResponse.status(),
                processResponse.message()
        );
    }
}
