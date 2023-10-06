package com.example.medicationmanagement.mapper;

import com.example.medicationmanagement.dto.MedicationDtoRequest;
import com.example.medicationmanagement.dto.MedicationDtoResponse;
import com.example.medicationmanagement.model.entities.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    @Mapping(target = "timeToTake", source = "timeToTake", qualifiedByName = "stringToLocalTime")
    @Mapping(target = "unit", source = "unit", qualifiedByName = "formatUnit")
    Medication toMedicationEntity(MedicationDtoRequest medicationDtoRequest);

    @Mapping(target = "timeToTake", source = "timeToTake", qualifiedByName = "localTimeToString")
    MedicationDtoResponse toMedicationDtoResponse(Medication medicationEntity);

    List<MedicationDtoResponse> toListDtoResponse(List<Medication> medications);

    @Named("stringToLocalTime")
    default LocalTime stringToLocalTime(String timeToTake) {
        return LocalTime.parse(timeToTake);
    }

    @Named("localTimeToString")
    default String localTimeToString(LocalTime timeToTake) {
        return timeToTake.toString();
    }

    @Named("formatUnit")
    default String unitFormat(String unit) {
        return unit.substring(0,1).toUpperCase() + unit.substring(1).toLowerCase();
    }
}
