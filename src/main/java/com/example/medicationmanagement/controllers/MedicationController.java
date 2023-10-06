package com.example.medicationmanagement.controllers;

import com.example.medicationmanagement.dto.MedicationDtoRequest;
import com.example.medicationmanagement.dto.MedicationDtoResponse;
import com.example.medicationmanagement.model.entities.Medication;
import com.example.medicationmanagement.services.MedicationService;
import com.example.medicationmanagement.validation.validators.MedicationValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/medications")
public class MedicationController {

    private final MedicationService medicationService;
    private final MedicationValidator medicationValidator;

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicationDtoResponse>> addMedicationsToPatient(
            @PathVariable Long patientId,
            @RequestBody @Valid List<MedicationDtoRequest> medications, BindingResult bindingResult) {

        List<MedicationDtoResponse> createdMedications = medicationService.addMedications(medications, patientId);

        return new ResponseEntity<>(createdMedications, HttpStatus.CREATED);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicationDtoResponse>> getMedicationsByPatient(@PathVariable Long patientId) {

        List<MedicationDtoResponse> medications = medicationService.getMedicationsByPatient(patientId);

        return ResponseEntity.ok(medications);
    }

    @PutMapping("/{medicationId}")
    public ResponseEntity<MedicationDtoResponse> updateMedication(
            @PathVariable Long medicationId,
            @RequestBody @Valid MedicationDtoRequest medicationUpdateDTO, BindingResult bindingResult) {

        medicationValidator.validateRequest(bindingResult);
        MedicationDtoResponse updatedMedication = medicationService.updateMedication(medicationUpdateDTO, medicationId);

        if (updatedMedication == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedMedication);
    }

    @DeleteMapping("/{medicationId}")
    public ResponseEntity<MedicationDtoResponse> deleteMedication(@PathVariable Long medicationId) {

        Medication medication = medicationService.getMedicationById(medicationId);

        medicationService.deleteMedication(medicationId);

        return ResponseEntity.noContent().build();
    }
}