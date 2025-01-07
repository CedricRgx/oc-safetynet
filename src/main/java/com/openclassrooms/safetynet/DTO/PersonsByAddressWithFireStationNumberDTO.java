package com.openclassrooms.safetynet.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The PhonesOfResidentsByFireStationDTO DTO class is used to communicate the list of phones of residents served by the fire station
 */
@Data
@Builder
public class PersonsByAddressWithFireStationNumberDTO {

    private String stationNumber;
    private List<PersonByAddressDTO> listOfPersonsByAddress;

}
