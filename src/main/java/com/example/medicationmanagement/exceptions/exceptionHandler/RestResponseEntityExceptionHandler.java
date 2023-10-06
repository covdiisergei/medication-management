package com.example.medicationmanagement.exceptions.exceptionHandler;

import com.example.medicationmanagement.exceptions.MedicationNotFoundException;
import com.example.medicationmanagement.exceptions.NotUniqueElementException;
import com.example.medicationmanagement.exceptions.PatientNotFoundException;
import com.example.medicationmanagement.exceptions.ValidationCustomException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({PatientNotFoundException.class})
    public ResponseEntity<ValidationErrorDetails> handlePatientNotFoundException(PatientNotFoundException ex) {
        ValidationErrorDetails errorDetails = new ValidationErrorDetails(LocalDate.now(), ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MedicationNotFoundException.class})
    public ResponseEntity<ValidationErrorDetails> handleMedicationNotFoundException(MedicationNotFoundException ex) {
        ValidationErrorDetails errorDetails = new ValidationErrorDetails(LocalDate.now(), ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ValidationErrorDetails> handleMedicationNotFoundException(ConstraintViolationException ex) {
        ValidationErrorDetails errorDetails = new ValidationErrorDetails(LocalDate.now(), ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotUniqueElementException.class})
    public ResponseEntity<ValidationErrorDetails> handleNotUniqueElementException(NotUniqueElementException ex) {
        ValidationErrorDetails errorDetails = new ValidationErrorDetails(LocalDate.now(), ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ValidationCustomException.class})
    public ResponseEntity<CustomValidationErrorDetails> handleValidationCustomException(ValidationCustomException ex, WebRequest request) {
        CustomValidationErrorDetails errorDetails = new CustomValidationErrorDetails(LocalDate.now(), ex.getErrorObjectMap(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DateTimeParseException.class})
    public ResponseEntity<ValidationErrorDetails> handleValidationCustomException(DateTimeParseException ex, WebRequest request) {
        ValidationErrorDetails errorDetails = new ValidationErrorDetails(LocalDate.now(), ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }



}
