package com.howwow.cpppaymentprocessorservice.business.service.impl;

import com.howwow.cpppaymentprocessorservice.business.service.EventPublisherService;
import com.howwow.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.howwow.cpppaymentprocessorservice.config.KafkaTopicConfig.PAYMENTS_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventPublisherServiceImpl implements EventPublisherService {
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void publish(PaymentEvent event) {
        try {
            kafkaTemplate.send(PAYMENTS_TOPIC, event.transactionId().toString(), event)
                    .whenComplete((result, ex) -> {
                        if (ex == null) {
                            log.info("Событие отправлено в Kafka: {}", event.transactionId());
                        } else {
                            log.warn("Не удалось отправить в Kafka: {}", event.transactionId(), ex);
                        }
                    });
        } catch (Exception ex) {
            log.warn("Kafka недоступна: {}", event.transactionId(), ex);
        }
    }
}
