package com.example.medicationmanagement.validation.validators;

import com.example.medicationmanagement.dto.PatientDtoRequest;
import com.example.medicationmanagement.exceptions.NotUniqueElementException;
import com.example.medicationmanagement.exceptions.ValidationCustomException;
import com.example.medicationmanagement.model.entities.Patient;
import com.example.medicationmanagement.model.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PatientValidator extends GlobalValidator {

    private final PatientRepository patientRepository;
    public void validateForUniqueness(PatientDtoRequest patientDtoRequest) {

        if (patientDtoRequest.getFirstName() != null && patientDtoRequest.getLastName() != null) {
            Optional<Patient> nameCheck = patientRepository.findByPatientName(patientDtoRequest.getFirstName(), patientDtoRequest.getLastName());
            if (nameCheck.isPresent()) {
               throw new NotUniqueElementException("The patient already exists in Database.");
            }
        }
    }
}