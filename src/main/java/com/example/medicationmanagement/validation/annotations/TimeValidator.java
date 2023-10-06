package com.example.medicationmanagement.validation.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeValidator implements ConstraintValidator<TimeConstraint, String> {
    private String message;

    @Override
    public void initialize(TimeConstraint constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String timeField, ConstraintValidatorContext context) {
        if(!timeField.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d$")){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }
        return true;
    }
}