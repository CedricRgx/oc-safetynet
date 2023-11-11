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

    private MedicalRecord medicalRecord;

    public Person(String firstName, String lastName, String phone, String zip, String address, String city, String email, MedicalRecord medicalRecord) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.zip = zip;
        this.address = address;
        this.city = city;
        this.email = email;
        this.medicalRecord = medicalRecord;
    }
}