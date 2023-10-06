package com.example.medicationmanagement.validation.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DataValidator implements ConstraintValidator<DateConstraint, String> {
    private String message;

    @Override
    public void initialize(DateConstraint constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String dataField, ConstraintValidatorContext context) {
        if (!dataField.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid date format. Please use yyyy-MM-dd format.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
