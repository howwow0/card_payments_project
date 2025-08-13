package com.howwow.cppnotificationservice.business.service;

import com.howwow.cppnotificationservice.kafka.dto.EmailMessage;

public interface EmailService {
    void sendEmail(EmailMessage emailMessage);
}
