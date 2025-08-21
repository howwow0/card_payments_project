package com.howwow.cppnotificationservice.business.exception;

import jakarta.mail.MessagingException;

public class EmailSendException extends RuntimeException {
    public EmailSendException(String s, MessagingException e) {
        super(s, e);
    }
}
