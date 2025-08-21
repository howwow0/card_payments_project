package com.howwow.cppnotificationservice.business.service.impl;

import com.howwow.cppnotificationservice.business.exception.EmailSendException;
import com.howwow.cppnotificationservice.business.service.EmailService;
import com.howwow.cppnotificationservice.business.service.PaymentTemplateEmailService;
import com.howwow.event.PaymentEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultEmailService implements EmailService {

    private final PaymentTemplateEmailService paymentTemplateEmailService;
    private final JavaMailSender mailSender;

    @Async
    public CompletableFuture<Void> sendEmail(PaymentEvent paymentEvent) {
        try {
            String html = paymentTemplateEmailService.generatePaymentEmail(paymentEvent);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(paymentEvent.email());
            helper.setSubject("Статус платежа #" + paymentEvent.transactionId());
            helper.setText(html, true);
            mailSender.send(message);
            return CompletableFuture.completedFuture(null);
        } catch (MessagingException e) {
            return CompletableFuture.failedFuture(new EmailSendException("Ошибка при отправке email", e));
        }
    }
}
