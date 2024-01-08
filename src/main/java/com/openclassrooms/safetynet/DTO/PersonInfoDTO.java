package com.openclassrooms.safetynet.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The PersonInfoDTO DTO class is used to communicate the info about a person
 */
@Data
@Builder
public class PersonInfoDTO {

    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;
}
