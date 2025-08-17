package com.howwow.cpppaymentorchestratorservice.bankgateway.client;

import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.response.PaymentAuthorizationGatewayResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "bank-gateway-service",
        url = "${bank-gateway.service.url}"
)
public interface BankGatewayClient {
    @PostMapping("/api/v1/bank/authorize")
    PaymentAuthorizationGatewayResponse authorizePayment(PaymentAuthorizationGatewayRequest request);
}
