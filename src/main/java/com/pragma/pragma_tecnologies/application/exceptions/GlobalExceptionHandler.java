package com.pragma.pragma_tecnologies.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura la excepción personalizada TechnologyAlreadyExistsException
    @ExceptionHandler(TechnologyAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleTechnologyAlreadyExistsException(TechnologyAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
