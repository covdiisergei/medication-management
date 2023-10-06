package com.example.medicationmanagement.exceptions.exceptionHandler;

import java.time.LocalDate;

public record ValidationErrorDetails(LocalDate date, String message) {
}
