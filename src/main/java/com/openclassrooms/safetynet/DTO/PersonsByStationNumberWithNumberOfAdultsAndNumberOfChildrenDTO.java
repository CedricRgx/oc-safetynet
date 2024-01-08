package com.openclassrooms.safetynet.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;
/**
 * The PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO DTO class is used to group a list of PersonByStationNumberDTO DTO, the number of adults in the list and the number of children
 */
@Data
@Builder
public class PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO {

    private int numberOfAdults;
    private int numberOfChildren;
    private List<PersonByStationNumberDTO> listOfPersonsByStationNumber;

}
