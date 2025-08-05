package com.howwow.cppcardgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CppCardGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CppCardGatewayApplication.class, args);
    }

}
