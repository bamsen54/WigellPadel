package com.simon.wigellpadel.exception;

public class AddressDoesNotExistException extends RuntimeException {
    public AddressDoesNotExistException(String message) {
        super(message);
    }
    public AddressDoesNotExistException(Long id) {
        super("Address with id " + id + " does not exist");
    }
}
