package com.example.medicationmanagement.mapper;

import com.example.medicationmanagement.dto.PatientDtoRequest;
import com.example.medicationmanagement.dto.PatientDtoResponse;
import com.example.medicationmanagement.model.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "dateOfBirth", source = "dateOfBirth",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "gender", source = "gender", qualifiedByName = "formatGender")
    Patient patientDtoToPatient(PatientDtoRequest patientDtoRequest);

    @Mapping(target = "dateOfBirth", source = "dateOfBirth",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "creationDate", source = "creationDate",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "modifyDate", source = "modifyDate",
            dateFormat = "yyyy-MM-dd")
    PatientDtoResponse patientToPatientDto(Patient patient);

    List<PatientDtoResponse> toPatientDtoList(List<Patient> patients);

    @Named("formatGender")
    default String formatGender(String gender) {
        return gender.substring(0,1).toUpperCase() + gender.substring(1).toLowerCase();
    }
}
