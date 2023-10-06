package com.example.medicationmanagement.dto;

import com.example.medicationmanagement.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PatientDtoResponse {

    private String firstName;

    private String lastName;

    private String gender;

    private String dateOfBirth;

    private String creationDate;

    private String modifyDate;

    private List<MedicationDtoResponse> medications;
}

