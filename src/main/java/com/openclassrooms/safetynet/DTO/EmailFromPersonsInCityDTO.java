package com.openclassrooms.safetynet.DTO;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The EmailFromPersonsInCityDTO DTO class is used to have the list of email of person in a city
 */
@Data
public class EmailFromPersonsInCityDTO {

    private static final Logger logger = LogManager.getLogger("EmailFromPersonsInCityDTO");

    private String email;
    private String city;
}