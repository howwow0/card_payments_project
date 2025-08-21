package com.howwow.cppnotificationservice.business.service;


import com.howwow.event.PaymentEvent;

import java.util.concurrent.CompletableFuture;


public interface EmailService {
    CompletableFuture<Void> sendEmail(PaymentEvent event);
}
