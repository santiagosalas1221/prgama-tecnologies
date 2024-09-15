package com.pragma.pragma_tecnologies.application.exceptions;

public class TechnologyAlreadyExistsException extends RuntimeException {
    public TechnologyAlreadyExistsException(String message) {
        super(message);
    }
}
