package com.howwow.cppnotificationservice.kafka.dto;

public record EmailMessage(String body,
                           String from,
                           String to) {
}
