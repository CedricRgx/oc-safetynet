package com.openclassrooms.safetynet.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FireStation {

    private String address;
    private String stationNumber;

    private List<Person> personByStation;

    public FireStation(String address, String stationNumber, List<Person> personByStation) {
        this.address = address;
        this.stationNumber = stationNumber;
        this.personByStation = personByStation;
    }
}