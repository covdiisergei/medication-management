package com.example.medicationmanagement.controllers;

import com.example.medicationmanagement.dto.PatientDtoRequest;
import com.example.medicationmanagement.dto.PatientDtoResponse;
import com.example.medicationmanagement.services.PatientService;

import com.example.medicationmanagement.validation.validators.PatientValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientValidator patientValidator;

    @PostMapping("/new")
    public ResponseEntity<PatientDtoResponse> createPatient
            (@RequestBody @Valid PatientDtoRequest patientDtoRequest, BindingResult bindingResult) {

        patientValidator.validateRequest(bindingResult);
        patientValidator.validateForUniqueness(patientDtoRequest);

        PatientDtoResponse newPatient = patientService.createPatient(patientDtoRequest);

        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDtoResponse> updatePatient(@PathVariable Long id,
                                                            @RequestBody @Valid PatientDtoRequest patientDtoRequest,
                                                            BindingResult bindingResult) {

        patientValidator.validateRequest(bindingResult);
        PatientDtoResponse patientDtoResponse = patientService.updatePatient(id, patientDtoRequest);

        return new ResponseEntity<>(patientDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDtoResponse> removePatient(@PathVariable Long id) {

        patientService.removePatient(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDtoResponse> getPatientById(@PathVariable Long id) {
        PatientDtoResponse patientDtoResponse= patientService.findPatientById(id);

        return new ResponseEntity<>(patientDtoResponse, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PatientDtoResponse>> getAllPatients() {

        List<PatientDtoResponse> patients = patientService.findAllPatients();

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientDtoResponse>> getAllPatientsBySearchTerm
            (@RequestParam("searchTerm") String searchTerm) {

        List<PatientDtoResponse> patients = patientService.findAllPatientsBySearchTerm(searchTerm);

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}