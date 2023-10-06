package com.example.medicationmanagement.dto;

import com.example.medicationmanagement.validation.annotations.DateConstraint;
import com.example.medicationmanagement.validation.annotations.GenderConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PatientDtoRequest {

    @NotBlank
    @Size(min = 3, message = "First name must be longer than 3 characters.")
    private String firstName;

    @NotBlank
    @Size(min = 3, message = "Last name must be longer than 3 characters.")
    private String lastName;

    @NotBlank
    @GenderConstraint
    private String gender;

    @NotBlank
    @DateConstraint(message = "Invalid date format. Please use yyyy-MM-dd format.")
    private String dateOfBirth;
}