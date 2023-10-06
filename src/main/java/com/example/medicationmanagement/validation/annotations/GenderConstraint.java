package com.example.medicationmanagement.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface GenderConstraint {

        String message() default "Invalid gender format. Please use one of these variants: (Male, Female, Divers)";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}
