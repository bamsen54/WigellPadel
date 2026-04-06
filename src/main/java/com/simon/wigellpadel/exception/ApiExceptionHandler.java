package com.simon.wigellpadel.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomerDoesNotExistException.class)
    public ResponseEntity<Map<String, Object>> handleCustomerNotExisting(CustomerDoesNotExistException ex, HttpServletRequest request) {
        String fullPath = request.getMethod() + " " + request.getRequestURI();
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), fullPath);
    }

    @ExceptionHandler(UsernameNotAvailableException.class)
    public ResponseEntity<Map<String, Object>> handleUserConflict(UsernameNotAvailableException ex, HttpServletRequest request) {
        String fullPath = request.getMethod() + " " + request.getRequestURI();
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), fullPath);
    }

    @ExceptionHandler(CustomerAlreadyHasAnAddressException.class)
    public ResponseEntity<Map<String, Object>> handleUserConflict(CustomerAlreadyHasAnAddressException ex, HttpServletRequest request) {
        String fullPath = request.getMethod() + " " + request.getRequestURI();
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), fullPath);
    }

    @ExceptionHandler(AddressDoesNotExistException.class)
    public ResponseEntity<Map<String, Object>> handleUserConflict(AddressDoesNotExistException ex, HttpServletRequest request) {
        String fullPath = request.getMethod() + " " + request.getRequestURI();
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), fullPath);
    }

    @ExceptionHandler(CourtDoesNotExistException.class)
    public ResponseEntity<Map<String, Object>> handleUserConflict(CourtDoesNotExistException ex, HttpServletRequest request) {
        String fullPath = request.getMethod() + " " + request.getRequestURI();
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), fullPath);
    }

    @ExceptionHandler(CourtNameNotAvailableException.class)
    public ResponseEntity<Map<String, Object>> handleUserConflict(CourtNameNotAvailableException ex, HttpServletRequest request) {
        String fullPath = request.getMethod() + " " + request.getRequestURI();
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), fullPath);
    }

    @ExceptionHandler(BookingConflictException.class)
    public ResponseEntity<Map<String, Object>> handleUserConflict(BookingConflictException ex, HttpServletRequest request) {
        String fullPath = request.getMethod() + " " + request.getRequestURI();
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), fullPath);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        String fullPath = request.getMethod() + " " + request.getRequestURI();
        if (ex.getMessage().contains("unique_customer_address")) {
            return buildResponse(HttpStatus.CONFLICT, "This customer already has this address", fullPath);
        }
        return buildResponse(HttpStatus.BAD_REQUEST, "Data integrity violation", fullPath);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message, String path) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", path);

        return new ResponseEntity<>(body, status);
    }
}