package com.howwow.cpppaymentprocessorservice.business.mapper;

import com.howwow.cpppaymentprocessorservice.business.entity.PaymentEventEntity;
import com.howwow.cpppaymentprocessorservice.business.entity.PaymentEventStatus;
import com.howwow.cpppaymentprocessorservice.rest.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentprocessorservice.rest.dto.response.PaymentProcessResponse;
import com.howwow.event.PaymentEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class PaymentProcessMapper {

    public PaymentEvent asPaymentEvent(PaymentProcessRequest req) {
        return new PaymentEvent(req.transactionId(),
                req.bankTransactionId(),
                req.merchantId(),
                req.amount(),
                req.currency(),
                req.email(),
                req.isApproved(),
                req.reason());
    }

    public PaymentEventEntity asPaymentEventEntity(PaymentProcessRequest paymentProcessRequest) {
        return PaymentEventEntity.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(Instant.now())
                .status(PaymentEventStatus.PENDING)
                .retryCount(0)
                .event(asPaymentEvent(paymentProcessRequest))
                .build();
    }

    public PaymentProcessResponse asPaymentProcessResponse(PaymentProcessRequest request, String status, String message) {
        return new PaymentProcessResponse(
                request.transactionId(),
                request.bankTransactionId(),
                status,
                message
        );
    }
}
