package com.howwow.cppauthrequest.client;

import com.howwow.cppauthrequest.client.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cppauthrequest.client.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cppauthrequest.config.GatewayFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "bank-gateway-service",
        url = "${bank-gateway.service.url}",
        configuration = GatewayFeignClientConfig.class
)
public interface GatewayClient {
    @PostMapping("/api/v1/bank/authorize")
    PaymentAuthorizationGatewayResponse authorizePayment(PaymentAuthorizationGatewayRequest request);
}
