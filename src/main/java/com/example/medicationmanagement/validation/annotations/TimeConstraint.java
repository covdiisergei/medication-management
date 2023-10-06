package com.example.medicationmanagement.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TimeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface TimeConstraint {

    String message() default "Invalid date format. Please use hh:mm format.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
