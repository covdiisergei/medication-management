package com.example.medicationmanagement.exceptions;

public class MedicationNotFoundException extends RuntimeException {
    public MedicationNotFoundException(String s) {
        super(s);
    }
}
