package com.howwow.cppnotificationservice.business.service;

import com.howwow.event.PaymentEvent;

public interface PaymentTemplateEmailService {
    String generatePaymentEmail(PaymentEvent event);
}
