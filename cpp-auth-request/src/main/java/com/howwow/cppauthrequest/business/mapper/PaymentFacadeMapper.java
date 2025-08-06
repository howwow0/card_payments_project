package com.howwow.cppauthrequest.business.mapper;

import com.howwow.cppauthrequest.client.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cppauthrequest.client.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cppauthrequest.persistence.enums.PaymentStatus;
import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;
import com.howwow.cppauthrequest.rest.dto.response.PaymentAuthorizationResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentFacadeMapper {

    public PaymentAuthorizationGatewayRequest toPaymentAuthorizationGatewayRequest(PaymentAuthorizationRequest paymentAuthorizationRequest) {
        return new PaymentAuthorizationGatewayRequest(
                paymentAuthorizationRequest.amount(),
                paymentAuthorizationRequest.currency(),
                paymentAuthorizationRequest.cardNumber(),
                paymentAuthorizationRequest.expiryDate(),
                paymentAuthorizationRequest.cvv()
        );
    }

    public PaymentAuthorizationResponse toPaymentAuthorizationResponse(UUID transactionId, PaymentAuthorizationGatewayResponse paymentAuthorizationGatewayResponse) {
        return new PaymentAuthorizationResponse(
                transactionId,
                paymentAuthorizationGatewayResponse.bankTransactionId(),
                PaymentStatus.isApproved(paymentAuthorizationGatewayResponse.isApproved()),
                paymentAuthorizationGatewayResponse.reason()
        );
    }
}
