package com.openclassrooms.safetynet.DTO;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The PhonesOfResidentsByFireStationDTO DTO class is used to communicate the list of phones of residents served by the fire station
 */
@Data
public class PersonsByAddressWithFireStationNumberDTO {
    private static final Logger logger = LogManager.getLogger("PersonsByAddressWithFireStationNumberDTO");

    private List<String> listOfStationNumber;
    private List<PersonByAddressDTO> listOfPersonsByAddress;

}
