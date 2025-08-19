package com.howwow.cpppaymentprocessorservice.business.entity;

import com.howwow.event.PaymentEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEventEntity implements Serializable {

    private String id;
    private Instant createdAt;
    private PaymentEventStatus status;
    private int retryCount;
    private PaymentEvent event;
}
