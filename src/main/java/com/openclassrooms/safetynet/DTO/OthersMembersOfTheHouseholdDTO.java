package com.openclassrooms.safetynet.DTO;

import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The OthersMembersOfTheHouseholdDTO DTO class is used to group the members of a household
 */
@Data
@Builder
public class OthersMembersOfTheHouseholdDTO {
    private static final Logger logger = LogManager.getLogger("OthersMembersOfTheHouseholdDTO");

    private String firstName;
    private String lastName;
    private String address;

    public OthersMembersOfTheHouseholdDTO(String firstName, String lastName, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
