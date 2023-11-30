package com.openclassrooms.safetynet.DTO;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The PersonInfoDTO DTO class is used to communicate the info about a person
 */
@Data
public class PersonInfoDTO {
    private static final Logger logger = LogManager.getLogger("PersonInfoDTO");

    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;
}
