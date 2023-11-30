package com.openclassrooms.safetynet.DTO;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The PhonesOfResidentsByFireStationDTO DTO class is used to communicate the list of phones of residents served by the fire station
 */
@Data
public class PhonesOfResidentsByFireStationDTO {
    private static final Logger logger = LogManager.getLogger("PhonesOfResidentsByFireStationDTO");

    private String phone;
    private String stationNumber;

}
