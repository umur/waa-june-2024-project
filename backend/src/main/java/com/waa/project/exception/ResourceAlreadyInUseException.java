package com.waa.project.exception;

public class ResourceAlreadyInUseException extends RuntimeException {
    public ResourceAlreadyInUseException(String message) {
        super(message);
    }
}
