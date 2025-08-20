package com.howwow.cpppaymentrecordingservice.kafka;

import com.howwow.cpppaymentrecordingservice.business.persistence.entity.PaymentEntity;
import com.howwow.cpppaymentrecordingservice.business.service.PaymentRecordingService;
import com.howwow.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentRecordingEventConsumer {

    private final PaymentRecordingService paymentRecordingService;

    @KafkaListener(topics = "payment-events", groupId = "payment-consumers-recording")
    public void consume(PaymentEvent event, Acknowledgment ack) {
        try {
            paymentRecordingService.createPayment(new PaymentEntity(
                    event.reason(),
                    event.isApproved(),
                    event.bankTransactionId(),
                    event.transactionId(),
                    event.email(),
                    event.merchantId(),
                    event.currency(),
                    event.amount()
            ));

            log.info("Событие {} успешно обработано", event.transactionId());
            ack.acknowledge();
        } catch (DataIntegrityViolationException e) {
            log.info("Событие {} уже обработано, пропускаем", event.transactionId());
            ack.acknowledge(); // подтверждаем дубликат, чтобы не доставлять снова
        } catch (Exception e) {
            log.error("Ошибка обработки события {}: {}", event.transactionId(), e.getMessage(), e);
            // ack не вызываем, Kafka повторит сообщение позже
        }
    }
}
