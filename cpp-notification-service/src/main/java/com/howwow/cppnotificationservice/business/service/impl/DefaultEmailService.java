package com.howwow.cppnotificationservice.business.service.impl;

import com.howwow.cppnotificationservice.business.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultEmailService implements EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            log.info("Письмо успешно отправлено на {}", to);
        } catch (Exception e) {
            log.error("Ошибка при отправке письма на {}: {}", to, e.getMessage(), e);
            // Можно добавить retry или запись в отдельную очередь для повторной отправки
        }
    }
}
