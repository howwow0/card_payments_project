package com.howwow.cpppaymentrecordingservice.kafka;

import com.howwow.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentRecordingEventConsumer {


    @KafkaListener(topics = "payment-events", groupId = "payment-consumers-recording")
    public void consume(PaymentEvent event, Acknowledgment ack) {
        try {
            //TO DO

            ack.acknowledge();
            log.info("Письмо для транзакции {} успешно отправлено", event.transactionId());
        } catch (Exception e) {
            log.error("Ошибка обработки транзакции {}: {}", event.transactionId(), e.getMessage(), e);
        }
    }
}
