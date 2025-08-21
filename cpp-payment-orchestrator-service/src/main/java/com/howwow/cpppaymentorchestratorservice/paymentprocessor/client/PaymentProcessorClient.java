package com.howwow.cpppaymentorchestratorservice.paymentprocessor.client;

import com.howwow.cpppaymentorchestratorservice.paymentprocessor.config.PaymentProcessFeignClientConfiguration;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentorchestratorservice.paymentprocessor.dto.response.PaymentProcessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "payment-processor-service",
        url = "${payment-processor.service.url}",
        configuration = PaymentProcessFeignClientConfiguration.class
)
public interface PaymentProcessorClient {
    @PostMapping("/api/v1/payment/process")
    PaymentProcessResponse processPayment(PaymentProcessRequest request);
}
