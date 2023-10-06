package com.example.medicationmanagement.exceptions;

import java.util.Map;

public class ValidationCustomException extends RuntimeException {
    private final Map<String, String> errors;

    public ValidationCustomException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrorObjectMap() {
        return errors;
    }
}
