package com.openclassrooms.safetynet.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

//The JSONDatabase class represents data in object format which will contain a list of classes containing the information from data.json.
@Data
@Component
public class JSONDatabase {
    List<Person> listOfPersons;
    List<MedicalRecords> listOfMedicalRecords;
    List<FireStation> listOfFireStations;
}
