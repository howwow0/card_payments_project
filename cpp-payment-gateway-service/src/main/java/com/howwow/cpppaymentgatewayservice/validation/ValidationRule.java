package com.howwow.cpppaymentgatewayservice.validation;

public interface ValidationRule<T> {
    boolean isValid(T t);
}
