package com.howwow.cppauthrequest.business.impl;

import com.howwow.cppauthrequest.business.AuthorizePaymentService;
import com.howwow.cppauthrequest.business.GatewayAdapterService;
import com.howwow.cppauthrequest.business.PaymentFacadeService;
import com.howwow.cppauthrequest.business.mapper.PaymentFacadeMapper;
import com.howwow.cppauthrequest.client.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;
import com.howwow.cppauthrequest.rest.dto.response.PaymentAuthorizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentFacadeServiceImpl implements PaymentFacadeService {

    private final AuthorizePaymentService authorizePaymentService;
    private final GatewayAdapterService gatewayAdapterService;
    private final PaymentFacadeMapper paymentFacadeMapper;

    @Override
    public PaymentAuthorizationResponse authorizePayment(PaymentAuthorizationRequest paymentAuthorizationRequest) {
        UUID transactionId = authorizePaymentService.authorize(paymentAuthorizationRequest);
        PaymentAuthorizationGatewayResponse response = gatewayAdapterService.authorize(
                paymentFacadeMapper.toPaymentAuthorizationGatewayRequest(paymentAuthorizationRequest));

        return paymentFacadeMapper.toPaymentAuthorizationResponse(transactionId, response);
    }

}
