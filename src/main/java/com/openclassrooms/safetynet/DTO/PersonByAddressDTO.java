package com.openclassrooms.safetynet.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The PersonByAddressDTO DTO class is used to group some person by the same address
 */
@Data
@Builder
public class PersonByAddressDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;
}
