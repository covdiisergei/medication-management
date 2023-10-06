package com.example.medicationmanagement.validation.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UnitValidator implements ConstraintValidator<UnitConstraint, String> {
    private String message;

    @Override
    public void initialize(UnitConstraint constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String unitField, ConstraintValidatorContext context) {
        if(!(unitField.equalsIgnoreCase("Grams") ||
                unitField.equalsIgnoreCase("Milligrams")
                || unitField.equalsIgnoreCase("Tablet"))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        };
        return true;
    }
}
