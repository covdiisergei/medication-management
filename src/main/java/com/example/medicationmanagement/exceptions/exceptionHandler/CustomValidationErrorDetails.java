package com.example.medicationmanagement.exceptions.exceptionHandler;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

public record CustomValidationErrorDetails(LocalDate timestamp, Map<String, String> errors, String message) {
}
