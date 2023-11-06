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

}