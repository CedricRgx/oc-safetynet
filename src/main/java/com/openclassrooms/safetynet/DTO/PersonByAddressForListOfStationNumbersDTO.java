package com.openclassrooms.safetynet.DTO;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The PersonByAddressForListOfStationNumbersDTO DTO class is used to get some info about a person
 */
@Data
public class PersonByAddressForListOfStationNumbersDTO {
    private static final Logger logger = LogManager.getLogger("PersonByAddressForListOfStationNumbersDTO");

    private String address;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;
}
