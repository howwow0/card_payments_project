package com.howwow.cppauthrequest.business;

import com.howwow.cppauthrequest.client.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cppauthrequest.client.dto.response.PaymentAuthorizationGatewayResponse;

public interface GatewayAdapterService {
    PaymentAuthorizationGatewayResponse authorize(PaymentAuthorizationGatewayRequest request);
}
