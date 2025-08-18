package com.howwow.cppnotificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CppNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CppNotificationServiceApplication.class, args);
    }

}
