package com.openclassrooms.safetynet.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

    private String firstName;
    private String lastName;
    private String phone;
    private String zip;
    private String address;
    private String city;
    private String email;

    private MedicalRecords medicalRecords;

}