package com.openclassrooms.safetynet.DTO;

import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The PersonByAddressDTO DTO class is used to group some person by the same address
 */
@Data
@Builder
public class PersonByAddressDTO {
    private static final Logger logger = LogManager.getLogger("PersonByAddressDTO");

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;
}
