package com.howwow.cpppaymentprocessorservice.business.service;


import com.howwow.event.PaymentEvent;


public interface EventPublisherService {
    void publish(PaymentEvent event);
}
