package com.openclassrooms.safetynet.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The ChildrenWithOthersMembersOfHouseholdDTO DTO class is used to have a children with the others members of the household
 */
@Data
@Builder
public class ChildrenWithOthersMembersOfHouseholdDTO {

    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private List<OthersMembersOfTheHouseholdDTO> listOfOthersMembersOfTheHouseholdDTO;

    public ChildrenWithOthersMembersOfHouseholdDTO(String firstName, String lastName, int age, String address, List<OthersMembersOfTheHouseholdDTO> listOfOthersMembersOfTheHouseholdDTO){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.listOfOthersMembersOfTheHouseholdDTO = listOfOthersMembersOfTheHouseholdDTO;
    }
}
