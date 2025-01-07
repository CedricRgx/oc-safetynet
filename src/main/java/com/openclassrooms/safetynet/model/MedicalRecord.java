package com.openclassrooms.safetynet.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The MedicalRecord class is the data model of the object MedicalRecord
 */
@Data
@Builder
public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }
}
