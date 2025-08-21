package com.howwow.cppnotificationservice.kafka;

import com.howwow.cppnotificationservice.business.service.EmailService;
import com.howwow.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailPaymentEventConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "payment-events", groupId = "payment-consumers-email")
    public void consume(PaymentEvent event, Acknowledgment ack) {
        var transactionId = event.transactionId();

        emailService.sendEmail(event)
                .thenRun(() -> {
                    log.info("Письмо для транзакции {} успешно отправлено", transactionId);
                    ack.acknowledge();
                })
                .exceptionally(ex -> {
                    log.error("Ошибка отправки письма {}: {}", transactionId, ex.getMessage());
                    return null;
                });
    }
}
