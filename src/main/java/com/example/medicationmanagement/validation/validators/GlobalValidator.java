package com.example.medicationmanagement.validation.validators;

import com.example.medicationmanagement.exceptions.ValidationCustomException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalValidator {

    public void validateRequest(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new LinkedHashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                if (!errors.containsKey(error.getField())) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
            }

            throw new ValidationCustomException(errors);
        }
    }
}
