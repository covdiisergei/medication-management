package com.example.medicationmanagement.dto;

import com.example.medicationmanagement.validation.annotations.TimeConstraint;
import com.example.medicationmanagement.validation.annotations.UnitConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class MedicationDtoRequest {

    @Valid
    @NotBlank
    @Size(min = 3, message = "Description must be longer than 3 characters.")
    private String description;

    @NotNull
    private Double dosage;

    @NotBlank
    @UnitConstraint
    private String unit;

    @NotBlank
    @TimeConstraint
    private String timeToTake;
}