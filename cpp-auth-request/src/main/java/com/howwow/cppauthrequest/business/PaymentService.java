package com.howwow.cppauthrequest.business;

import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;

import java.util.UUID;

public interface PaymentService {
    UUID authorize(PaymentAuthorizationRequest paymentAuthorizationRequest);
}
