package com.simon.wigellpadel.exception;

public class CustomerDoesNotExistException extends RuntimeException {
    public CustomerDoesNotExistException(String message) {
        super(message);
    }
    public CustomerDoesNotExistException(Long id) {
        super( "Customer with id: " + id + " does not exist" );
    }

}
