package com.simon.wigellpadel.exception;

public class CourtDoesNotExistException extends RuntimeException {
    public CourtDoesNotExistException(String message) {
        super(message);
    }
    public CourtDoesNotExistException(Long id) {
        super("Court with id " + id + " does not exist");
    }
}
