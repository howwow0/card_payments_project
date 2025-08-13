package com.howwow.cppnotificationservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howwow.cppnotificationservice.business.service.EmailService;
import com.howwow.cppnotificationservice.kafka.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailKafkaListener {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @KafkaListener(topics = "email.notifications", groupId = "email-service")
    public void listen(String message) {
        try {
            EmailMessage email = objectMapper.readValue(message, EmailMessage.class);
            log.info("Получено сообщение для отправки email: {}", email);
            emailService.sendEmail(email);
        } catch (Exception e) {
            log.error("Ошибка обработки сообщения из Kafka", e);
        }
    }
}
