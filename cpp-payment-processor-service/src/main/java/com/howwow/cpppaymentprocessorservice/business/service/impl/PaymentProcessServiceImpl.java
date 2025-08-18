package com.howwow.cpppaymentprocessorservice.business.service.impl;

import com.howwow.cpppaymentprocessorservice.business.mapper.PaymentProcessMapper;
import com.howwow.cpppaymentprocessorservice.business.service.PaymentProcessService;
import com.howwow.cpppaymentprocessorservice.rest.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentprocessorservice.rest.dto.response.PaymentProcessResponse;

import com.howwow.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.howwow.cpppaymentprocessorservice.config.KafkaTopicConfig.PAYMENTS_TOPIC;

@Service
@RequiredArgsConstructor
public class PaymentProcessServiceImpl implements PaymentProcessService {


    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PaymentProcessMapper paymentProcessMapper;

    @Override
    public PaymentProcessResponse processPayment(PaymentProcessRequest request) {
        String status = Boolean.TRUE.equals(request.isApproved()) ? "SUCCESS" : "FAILED";
        String message = Boolean.TRUE.equals(request.isApproved()) ?
                "Транзакция завершена успешно: %s".formatted(request.reason()) :
                request.reason();

        PaymentEvent event = paymentProcessMapper.asPaymentEvent(request);

        kafkaTemplate.executeInTransaction(operations -> {
            operations.send(PAYMENTS_TOPIC, request.transactionId().toString(), event);
            return true;
        });

        return paymentProcessMapper.asPaymentProcessResponse(request, status, message);
    }
}
