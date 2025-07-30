package com.howwow.cppauthrequest.business;

import com.howwow.cppauthrequest.client.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;

public interface GatewayAdapterService {
    PaymentAuthorizationGatewayResponse authorize(PaymentAuthorizationRequest request);
}
