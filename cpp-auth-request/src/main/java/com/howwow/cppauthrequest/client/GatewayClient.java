package com.howwow.cppauthrequest.client;

import com.howwow.cppauthrequest.client.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "gateway-service",
        url = "${gateway.service.url}"
)
public interface GatewayClient {
    @PostMapping("/api/v1/bank/authorize")
    PaymentAuthorizationGatewayResponse authorizePayment(PaymentAuthorizationRequest request);
}
