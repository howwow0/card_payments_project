package com.howwow.cppcarddecryptionstarter.carddecrypt.exception;

public class DecryptedException extends RuntimeException {
    public DecryptedException(Exception e) {
        super(e);
    }
}
