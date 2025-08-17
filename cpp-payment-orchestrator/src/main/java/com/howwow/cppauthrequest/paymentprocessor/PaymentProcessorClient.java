package com.howwow.cppauthrequest.paymentprocessor;

import com.howwow.cppauthrequest.bankgateway.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cppauthrequest.bankgateway.dto.response.PaymentAuthorizationGatewayResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "payment-processor-service",
        url = "${payment-processor.service.url}"
)
public interface PaymentProcessorClient {
    @PostMapping("/api/v1/payment/process")
    PaymentAuthorizationGatewayResponse processPayment(PaymentAuthorizationGatewayRequest request);
}
