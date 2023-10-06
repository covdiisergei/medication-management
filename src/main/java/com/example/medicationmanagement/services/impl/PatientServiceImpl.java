package com.example.medicationmanagement.services.impl;

import com.example.medicationmanagement.dto.PatientDtoRequest;
import com.example.medicationmanagement.dto.PatientDtoResponse;
import com.example.medicationmanagement.exceptions.PatientNotFoundException;
import com.example.medicationmanagement.mapper.PatientMapper;
import com.example.medicationmanagement.model.entities.Patient;
import com.example.medicationmanagement.model.repositories.PatientRepository;
import com.example.medicationmanagement.services.PatientService;
import com.example.medicationmanagement.validation.validators.PatientValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientValidator patientValidator;
    private final PatientMapper patientMapper;

    @Override
    @Transactional
    public PatientDtoResponse createPatient(PatientDtoRequest patientDtoRequest) {
        Patient patient = patientMapper.patientDtoToPatient(patientDtoRequest);
        patient.setCreationDate(LocalDate.now());
        patient.setModifyDate(LocalDate.now());

        Patient savedPatient = patientRepository.save(patient);

        return patientMapper.patientToPatientDto(savedPatient);
    }

    @Override
    @Transactional
    public PatientDtoResponse updatePatient(Long id, PatientDtoRequest patientDtoRequest) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id " + id + " not found"));

         patient.setFirstName(patientDtoRequest.getFirstName());
         patient.setLastName(patientDtoRequest.getLastName());
         patient.setDateOfBirth(LocalDate.parse(patientDtoRequest.getDateOfBirth()));
         patient.setGender(patientDtoRequest.getGender());
         patient.setModifyDate(LocalDate.now());

        return patientMapper.patientToPatientDto(patientRepository.save(patient));
    }

    @Override
    @Transactional
    public void removePatient(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if(patient.isEmpty()) {
            throw new PatientNotFoundException("Patient with id " + id + " not found");
        }

        patientRepository.deleteById(id);

    }
    @Override
    public List<PatientDtoResponse> findAllPatients() {
        List<Patient> patients = (List<Patient>) patientRepository.findAll();
        patients.sort(Comparator.comparing(Patient::getLastName).thenComparing(Patient::getFirstName));

        return patients.stream().map(patientMapper::patientToPatientDto).toList();
    }

    @Override
    public PatientDtoResponse findPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if(patient.isEmpty()) {
            throw new PatientNotFoundException("Patient with id " + id + " not found.");
        }

        return patientMapper.patientToPatientDto(patient.get());

    }

    @Override
    public List<PatientDtoResponse> findAllPatientsBySearchTerm(String searchTerm) {
        List<Patient> patients = patientRepository.findAllBySearchTerm(searchTerm);
        return patientMapper.toPatientDtoList(patients);
    }

}
