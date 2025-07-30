package com.howwow.cppauthrequest.client;

import com.howwow.cppauthrequest.client.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cppauthrequest.client.dto.response.PaymentAuthorizationGatewayResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "gateway-service",
        url = "${gateway.service.url}"
)
public interface GatewayClient {
    @PostMapping("/api/v1/bank/authorize")
    PaymentAuthorizationGatewayResponse authorizePayment(PaymentAuthorizationGatewayRequest request);
}
