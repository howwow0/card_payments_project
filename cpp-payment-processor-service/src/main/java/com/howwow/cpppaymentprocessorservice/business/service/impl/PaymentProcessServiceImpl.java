package com.howwow.cpppaymentprocessorservice.business.service.impl;

import com.howwow.cpppaymentprocessorservice.business.mapper.PaymentProcessMapper;
import com.howwow.cpppaymentprocessorservice.business.service.EventPublisherService;
import com.howwow.cpppaymentprocessorservice.business.service.PaymentProcessService;
import com.howwow.cpppaymentprocessorservice.rest.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentprocessorservice.rest.dto.response.PaymentProcessResponse;
import com.howwow.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessServiceImpl implements PaymentProcessService {

    private final PaymentProcessMapper paymentProcessMapper;
    private final EventPublisherService eventPublisherService;
    @Override
    public PaymentProcessResponse processPayment(PaymentProcessRequest request) {
        String status = Boolean.TRUE.equals(request.isApproved()) ? "SUCCESS" : "FAILED";
        String message = Boolean.TRUE.equals(request.isApproved()) ?
                "Транзакция завершена успешно: %s".formatted(request.reason()) :
                request.reason();

        PaymentEvent event = paymentProcessMapper.asPaymentEvent(request);
        eventPublisherService.publish(event);
        return paymentProcessMapper.asPaymentProcessResponse(request, status, message);
    }
}
