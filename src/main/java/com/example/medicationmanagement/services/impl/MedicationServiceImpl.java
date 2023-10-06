package com.example.medicationmanagement.services.impl;

import com.example.medicationmanagement.dto.MedicationDtoRequest;
import com.example.medicationmanagement.dto.MedicationDtoResponse;
import com.example.medicationmanagement.exceptions.MedicationNotFoundException;
import com.example.medicationmanagement.exceptions.PatientNotFoundException;
import com.example.medicationmanagement.mapper.MedicationMapper;
import com.example.medicationmanagement.model.entities.Medication;
import com.example.medicationmanagement.model.entities.Patient;
import com.example.medicationmanagement.model.repositories.MedicationRepository;
import com.example.medicationmanagement.model.repositories.PatientRepository;
import com.example.medicationmanagement.services.MedicationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationMapper medicationMapper;
    private final MedicationRepository medicationRepository;
    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public List<MedicationDtoResponse> addMedications(List<MedicationDtoRequest> medications, Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id " + patientId + "not found."));

        return medications.stream()
                .map(medicationMapper::toMedicationEntity)
                .peek(medicationEntity -> {
                    medicationEntity.setCreationDate(LocalDate.now());
                    medicationEntity.setModifyDate(LocalDate.now());
                    patient.addMedicationsToPatient(medicationEntity);
                })
                .map(medicationRepository::save)
                .map(medicationMapper::toMedicationDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MedicationDtoResponse updateMedication(MedicationDtoRequest medicationDtoUpdateRequest, Long medicationId) {

        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new MedicationNotFoundException("Medication with id " + medicationId + " not found."));

        medication.setDescription(medicationDtoUpdateRequest.getDescription());
        medication.setDosage(medicationDtoUpdateRequest.getDosage());
        medication.setUnit(medicationDtoUpdateRequest.getUnit());
        medication.setModifyDate(LocalDate.now());

        return medicationMapper.toMedicationDtoResponse(medicationRepository.save(medication));
    }

    @Override
    @Transactional
    public void deleteMedication(Long medicationId) {

        medicationRepository.deleteMedicationByPatientIdAndMedicationId(medicationId);
    }

    @Override
    public List<MedicationDtoResponse> getMedicationsByPatient(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id " + patientId + "not found."));

        List<Medication> medications = medicationRepository.getMedicationsByPatientId(patientId);

        return medicationMapper.toListDtoResponse(medications);
    }

    @Override
    public Medication getMedicationById(Long medicationId) {
        return medicationRepository.findById(medicationId)
                .orElseThrow(() -> new MedicationNotFoundException("Medication with ID " + medicationId + "not found."));
    }
}
