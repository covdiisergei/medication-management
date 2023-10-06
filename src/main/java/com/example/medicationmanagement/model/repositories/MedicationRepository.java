package com.example.medicationmanagement.model.repositories;

import com.example.medicationmanagement.model.entities.Medication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, Long> {

    @Query("SELECT medication from Medication medication where medication.patient.id = :id")
    List<Medication> getMedicationsByPatientId(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Medication m WHERE m.id = :medicationId")
    void deleteMedicationByPatientIdAndMedicationId
            (@Param("medicationId") Long medicationId);

}
