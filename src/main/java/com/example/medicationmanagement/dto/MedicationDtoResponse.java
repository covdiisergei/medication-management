package com.example.medicationmanagement.dto;

import com.example.medicationmanagement.model.enums.Unit;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDtoResponse {

    private Long id;

    private String description;

    private Double dosage;

    private String unit;

    private String timeToTake;

    private LocalDate creationDate;

    private LocalDate modifyDate;
}
