package com.example.medicationmanagement.services;

import com.example.medicationmanagement.dto.PatientDtoRequest;
import com.example.medicationmanagement.dto.PatientDtoResponse;
import com.example.medicationmanagement.model.entities.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface PatientService {

    PatientDtoResponse createPatient(PatientDtoRequest patientDtoRequest);

    PatientDtoResponse updatePatient(Long id, PatientDtoRequest patientDtoRequest);

    void removePatient (Long id);

    List<PatientDtoResponse> findAllPatients();

    PatientDtoResponse findPatientById(@Param("patientId") Long patientId);

    List<PatientDtoResponse> findAllPatientsBySearchTerm(String searchTerm);
}
