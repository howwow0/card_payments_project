package com.howwow.cpppaymentprocessorservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String PAYMENTS_TOPIC = "payment-events";

    @Bean
    public NewTopic paymentEventsTopic() {
        return TopicBuilder.name(PAYMENTS_TOPIC)
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }
}
