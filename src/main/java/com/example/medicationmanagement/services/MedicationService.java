package com.example.medicationmanagement.services;

import com.example.medicationmanagement.dto.MedicationDtoRequest;
import com.example.medicationmanagement.dto.MedicationDtoResponse;
import com.example.medicationmanagement.model.entities.Medication;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationService {

    List<MedicationDtoResponse> addMedications(List<MedicationDtoRequest> medications, Long patientId);

    MedicationDtoResponse updateMedication (MedicationDtoRequest medicationDtoUpdateRequest, Long patientId);


    void deleteMedication(Long medicationId);

    List<MedicationDtoResponse> getMedicationsByPatient(Long patientId);

    Medication getMedicationById(Long medicationId);
}
