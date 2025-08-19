package com.howwow.cpppaymentrecordingservice.business.service;


import com.howwow.cpppaymentrecordingservice.business.persistence.entity.PaymentEntity;
import com.howwow.cpppaymentrecordingservice.rest.dto.response.PaymentResponse;

import java.util.UUID;

public interface PaymentRecordingService {
    PaymentEntity createPayment(PaymentEntity paymentEntity);
    PaymentResponse getPayment(UUID paymentId);
}
