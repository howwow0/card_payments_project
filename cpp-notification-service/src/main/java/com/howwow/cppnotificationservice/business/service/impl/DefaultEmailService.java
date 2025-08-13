package com.howwow.cppnotificationservice.business.service.impl;

import com.howwow.cppnotificationservice.business.service.EmailService;
import com.howwow.cppnotificationservice.kafka.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultEmailService implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.to());
        message.setSubject(emailMessage.from());
        message.setText(emailMessage.body());

        mailSender.send(message);
        log.info("Письмо успешно отправлено на {}", emailMessage.to());
    }
}
