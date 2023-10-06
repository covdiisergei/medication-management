package com.example.medicationmanagement.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Double dosage;

    @Column
    private String unit;

    @Column
    private LocalTime timeToTake;

    @Column
    @PastOrPresent
    private LocalDate creationDate;

    @Column
    @PastOrPresent
    private LocalDate modifyDate;

    @ManyToOne()
    @JoinColumn(name = "patient_id")
    Patient patient;
}