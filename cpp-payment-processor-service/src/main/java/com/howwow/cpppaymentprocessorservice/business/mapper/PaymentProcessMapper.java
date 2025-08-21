package com.howwow.cpppaymentprocessorservice.business.mapper;

import com.howwow.cpppaymentprocessorservice.rest.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentprocessorservice.rest.dto.response.PaymentProcessResponse;
import com.howwow.event.PaymentEvent;
import org.springframework.stereotype.Component;


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

    public PaymentProcessResponse asPaymentProcessResponse(PaymentProcessRequest request, String status, String message) {
        return new PaymentProcessResponse(
                request.transactionId(),
                request.bankTransactionId(),
                status,
                message
        );
    }
}
