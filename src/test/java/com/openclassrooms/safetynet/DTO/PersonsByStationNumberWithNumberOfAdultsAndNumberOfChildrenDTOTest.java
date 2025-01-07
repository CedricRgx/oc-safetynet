package com.openclassrooms.safetynet.DTO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTOTest {

    private static PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO DTOForTest;

    int nbAdultsToTest;
    int nbChildrenToTest;
    List<PersonByStationNumberDTO> listToTest;

    @BeforeAll
    static void createDTOForTest(){
        int nbAdultsToTest = 3;
        int nbChildrenToTest = 4;
        List<PersonByStationNumberDTO> listToTest = new ArrayList<>(); //{{add("Adulte", "Petit", "15 rue de la rue", "666-777-8888", "12/06/1975");add("Adulte", "Petit", "15 rue de la rue", "666-777-8888", "12/06/1999");}};
        DTOForTest = new PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO(nbAdultsToTest, nbChildrenToTest, listToTest);
    }

    @Test
    void initializeBuilderTest(){
        assertEquals(DTOForTest, PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO.builder()
                .numberOfAdults(3)
                .numberOfChildren(4)
                .listOfPersonsByStationNumber(new ArrayList<>()).build());
    }

    @Test
    void setAndGetNumberOfAdultsTest(){
        nbAdultsToTest = 3;
        DTOForTest.setNumberOfAdults(nbAdultsToTest);
        assertEquals(nbAdultsToTest, DTOForTest.getNumberOfAdults());
    }

    @Test
    void setAndGetNumberOfChildrenTest(){
        nbChildrenToTest = 4;
        DTOForTest.setNumberOfChildren(nbChildrenToTest);
        assertEquals(nbChildrenToTest, DTOForTest.getNumberOfChildren());
    }

    @Test
    void setAndGetListOfPersonsByStationNumberTest() {
        PersonByStationNumberDTO personByStationNumberOne = new PersonByStationNumberDTO(
                "Adulte",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                "12/06/1975");
        PersonByStationNumberDTO personByStationNumberTwo = new PersonByStationNumberDTO(
                "Adulte",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                "12/06/1975");
        listToTest = new ArrayList<>(){{add(personByStationNumberOne);add(personByStationNumberTwo);}};

        DTOForTest.setListOfPersonsByStationNumber(listToTest);
        assertEquals(listToTest, DTOForTest.getListOfPersonsByStationNumber());
    }

    @Test
    public void equalsTrueTest(){
        PersonByStationNumberDTO personByStationNumberOne = new PersonByStationNumberDTO(
                "Adulte",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                "12/06/1975");
        PersonByStationNumberDTO personByStationNumberTwo = new PersonByStationNumberDTO(
                "Adulte",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                "12/06/1975");
        listToTest = new ArrayList<>(){{add(personByStationNumberOne);add(personByStationNumberTwo);}};

        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO DTOForTestOne = new PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO(4, 3, listToTest);
        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO DTOForTestTwo = new PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO(4, 3, listToTest);

        assertEquals(DTOForTestOne, DTOForTestTwo);
        assertEquals(DTOForTestOne.hashCode(), DTOForTestTwo.hashCode());
    }

    @Test
    public void equalsNullTest(){
        PersonByStationNumberDTO personByStationNumberOne = new PersonByStationNumberDTO(
                "Adulte",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                "12/06/1975");
        PersonByStationNumberDTO personByStationNumberTwo = new PersonByStationNumberDTO(
                "Adulte",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                "12/06/1975");
        listToTest = new ArrayList<>(){{add(personByStationNumberOne);add(personByStationNumberTwo);}};

        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO DTOForTestOne = new PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO(4, 3, listToTest);
        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO DTOForTestTwo = null;

        assertNull(DTOForTestTwo);
        assertNotEquals(DTOForTestOne, DTOForTestTwo);
    }
}
