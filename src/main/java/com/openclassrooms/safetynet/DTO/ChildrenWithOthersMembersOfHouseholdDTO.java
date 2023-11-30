package com.openclassrooms.safetynet.DTO;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The ChildrenWithOthersMembersOfHouseholdDTO DTO class is used to have a children with the others members of the household
 */
@Data
public class ChildrenWithOthersMembersOfHouseholdDTO {

    private static final Logger logger = LogManager.getLogger("ChildrenWithOthersMembersOfHouseholdDTO");

    private String firstName;
    private String lastName;
    private String age;
    private String address;
    private List<OthersMembersOfTheHouseholdDTO> listOfOthersMembersOfTheHouseholdDTO;

    public ChildrenWithOthersMembersOfHouseholdDTO(String firstName, String lastName, String age, String address, List<OthersMembersOfTheHouseholdDTO> listOfOthersMembersOfTheHouseholdDTO){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.listOfOthersMembersOfTheHouseholdDTO = listOfOthersMembersOfTheHouseholdDTO;
    }
}
