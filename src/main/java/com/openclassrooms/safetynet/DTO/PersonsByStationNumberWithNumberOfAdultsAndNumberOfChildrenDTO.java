package com.openclassrooms.safetynet.DTO;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
/**
 * The PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO DTO class is used to group a list of PersonByStationNumberDTO DTO, the number of adults in the list and the number of children
 */
@Data
public class PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO {

    private static final Logger logger = LogManager.getLogger("PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO");

    private int numberOfAdults;
    private int numberOfChildren;
    private List<PersonByStationNumberDTO> listOfPersonsByStationNumber;

}
