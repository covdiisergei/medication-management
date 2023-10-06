package com.example.medicationmanagement.model.repositories;

import com.example.medicationmanagement.dto.PatientDtoResponse;
import com.example.medicationmanagement.model.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query("SELECT patient FROM Patient patient WHERE patient.firstName LIKE %:searchTerm% OR patient.lastName LIKE %:searchTerm%")
    List<Patient> findAllBySearchTerm(@Param("searchTerm") String searchString);

    @Query("SELECT patient FROM Patient patient WHERE patient.firstName = :firstName AND patient.lastName = :lastName")
    Optional<Patient> findByPatientName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
