package com.howwow.carddecryptionstarter.business.exception;

public class DecryptedException extends RuntimeException {
    public DecryptedException(Exception e) {
        super(e);
    }
}
