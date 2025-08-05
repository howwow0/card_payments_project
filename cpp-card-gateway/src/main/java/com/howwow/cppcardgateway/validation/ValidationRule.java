package com.howwow.cppcardgateway.validation;

public interface ValidationRule<T> {
    boolean isValid(T t);
}
