package com.simon.wigellpadel.exception;

public class CustomerAlreadyHasAnAddressException extends RuntimeException {
    public CustomerAlreadyHasAnAddressException(String message) {
        super(message);
    }
}
