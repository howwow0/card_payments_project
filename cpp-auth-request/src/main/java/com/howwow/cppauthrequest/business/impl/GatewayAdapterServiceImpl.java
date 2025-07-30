package com.howwow.cppauthrequest.business.impl;

import com.howwow.cppauthrequest.business.GatewayAdapterService;
import com.howwow.cppauthrequest.client.GatewayClient;
import com.howwow.cppauthrequest.client.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GatewayAdapterServiceImpl implements GatewayAdapterService {
    private final GatewayClient gatewayClient;

    @Override
    public PaymentAuthorizationGatewayResponse authorize(PaymentAuthorizationRequest request) {
        return gatewayClient.authorizePayment(request);
    }
}
