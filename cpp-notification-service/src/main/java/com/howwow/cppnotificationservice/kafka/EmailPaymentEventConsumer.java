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
        try {
            String status = event.isApproved() ? "успешно" : "неуспешно";
            String subject = "Статус вашей транзакции: " + status;
            String body = String.format(
                    "Здравствуйте!\n\n" +
                            "Ваш платеж с ID %s был обработан.\n" +
                            "Статус: %s\n" +
                            "Причина: %s\n\n" +
                            "Спасибо за использование нашего сервиса.",
                    event.transactionId(),
                    status,
                    event.reason()
            );

            emailService.sendEmail(event.email(), subject, body);

            ack.acknowledge();
            log.info("Письмо для транзакции {} успешно отправлено", event.transactionId());
        } catch (Exception e) {
            log.error("Ошибка обработки транзакции {}: {}", event.transactionId(), e.getMessage(), e);
        }
    }
}
