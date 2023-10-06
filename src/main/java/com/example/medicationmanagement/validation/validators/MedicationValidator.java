package com.example.medicationmanagement.validation.validators;

import com.example.medicationmanagement.dto.MedicationDtoRequest;
import com.example.medicationmanagement.dto.PatientDtoRequest;
import com.example.medicationmanagement.exceptions.NotUniqueElementException;
import com.example.medicationmanagement.exceptions.ValidationCustomException;
import com.example.medicationmanagement.model.entities.Patient;
import com.example.medicationmanagement.model.repositories.MedicationRepository;
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
public class MedicationValidator extends GlobalValidator{
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    public void validateForUniqueness(MedicationDtoRequest medicationDtoRequest) {

    }
}
