package com.howwow.cppsecurityservice.business.exception;

public class EncryptedException extends RuntimeException {
    public EncryptedException(Exception e) {
        super(e);
    }
}
