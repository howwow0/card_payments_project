package com.howwow.cpppaymentprocessorservice.business.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.howwow.cpppaymentprocessorservice.business.entity.PaymentEventEntity;
import com.howwow.cpppaymentprocessorservice.business.mapper.PaymentProcessMapper;
import com.howwow.cpppaymentprocessorservice.business.service.PaymentProcessService;
import com.howwow.cpppaymentprocessorservice.rest.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentprocessorservice.rest.dto.response.PaymentProcessResponse;
import com.howwow.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.howwow.cpppaymentprocessorservice.config.KafkaTopicConfig.PAYMENTS_TOPIC;

@Service
@RequiredArgsConstructor
public class PaymentProcessServiceImpl implements PaymentProcessService {


    private final PaymentProcessMapper paymentProcessMapper;
    private final RedisTemplate<String, PaymentEventEntity> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public PaymentProcessResponse processPayment(PaymentProcessRequest request) {
        String status = Boolean.TRUE.equals(request.isApproved()) ? "SUCCESS" : "FAILED";
        String message = Boolean.TRUE.equals(request.isApproved()) ?
                "Транзакция завершена успешно: %s".formatted(request.reason()) :
                request.reason();

        PaymentEventEntity entity = paymentProcessMapper.asPaymentEventEntity(request);

        redisTemplate.opsForStream()
                .add("payment-events-stream", Map.of("event", entity));
        return paymentProcessMapper.asPaymentProcessResponse(request, status, message);
    }
}
