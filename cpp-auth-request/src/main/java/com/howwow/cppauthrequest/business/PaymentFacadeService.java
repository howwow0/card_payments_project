package com.howwow.cppauthrequest.business;

import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;
import com.howwow.cppauthrequest.rest.dto.response.PaymentAuthorizationResponse;

public interface PaymentFacadeService {
    PaymentAuthorizationResponse authorizePayment(PaymentAuthorizationRequest paymentAuthorizationRequest);
}
