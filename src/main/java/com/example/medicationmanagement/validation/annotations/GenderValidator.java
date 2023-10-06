package com.example.medicationmanagement.validation.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<GenderConstraint, String> {
    private String message;

    @Override
    public void initialize(GenderConstraint constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String genderField, ConstraintValidatorContext context) {
        if (!(genderField.equalsIgnoreCase("Male") ||
                genderField.equalsIgnoreCase("Female") ||
                genderField.equalsIgnoreCase("Divers"))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }
        return true;
    }
}
