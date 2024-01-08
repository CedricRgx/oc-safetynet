package com.openclassrooms.safetynet.DTO;

import lombok.Builder;
import lombok.Data;

/**
 * The PersonByStationNumberDTO DTO class is used to group some person by the same station number
 */
@Data
@Builder
public class PersonByStationNumberDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String birthday;

}
