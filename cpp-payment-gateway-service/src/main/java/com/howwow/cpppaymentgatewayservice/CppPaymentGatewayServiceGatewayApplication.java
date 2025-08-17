package com.howwow.cpppaymentgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CppPaymentGatewayServiceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CppPaymentGatewayServiceGatewayApplication.class, args);
    }

}
