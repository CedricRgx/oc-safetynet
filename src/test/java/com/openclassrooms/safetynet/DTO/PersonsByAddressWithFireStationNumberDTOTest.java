package com.openclassrooms.safetynet.DTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonsByAddressWithFireStationNumberDTOTest {
    private static final Logger logger = LogManager.getLogger("PersonsByAddressWithFireStationNumberDTOTest");

    private static PersonsByAddressWithFireStationNumberDTO DTOForTest;

    String valueToTest;
    List<PersonByAddressDTO> listToTest;

    @BeforeAll
    static void createDTOForTest(){
        logger.info("Create PersonsByAddressWithFireStationNumberDTO for test");
        String stationNumber = "3";
        List<PersonByAddressDTO> listPersonByAddressDTOToTest = new ArrayList<>();
        DTOForTest = new PersonsByAddressWithFireStationNumberDTO(stationNumber, listPersonByAddressDTOToTest);
    }

    @Test
    void initializeBuilderTest(){
        logger.info("Test initializeBuilder for PersonsByAddressWithFireStationNumberDTO");
        assertEquals(DTOForTest, PersonsByAddressWithFireStationNumberDTO.builder()
                .stationNumber("3")
                .listOfPersonsByAddress(new ArrayList<>()).build());
    }

    @Test
    void setAndGetStationNumberTest(){
        logger.info("Test setAndGetEmail for PersonsByAddressWithFireStationNumberDTO");
        valueToTest = "3";
        DTOForTest.setStationNumber(valueToTest);
        assertEquals(valueToTest, DTOForTest.getStationNumber());
    }

    @Test
    void setAndGetListOfPersonByAddressDTOTest() {
        logger.info("Test setAndGetListOfPersonByAddressDTO for PersonsByAddressWithFireStationNumberDTO");
        PersonByAddressDTO personByAddressOne = new PersonByAddressDTO(
                "Enfant",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                33,
                new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}},
                new ArrayList<>() {{add("nillacilan");}});
        PersonByAddressDTO personByAddressTwo = new PersonByAddressDTO(
                "Enfant",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                33,
                new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}},
                new ArrayList<>() {{add("nillacilan");}});
        listToTest = new ArrayList<>(){{add(personByAddressOne);add(personByAddressTwo);}};

        DTOForTest.setListOfPersonsByAddress(listToTest);
        assertEquals(listToTest, DTOForTest.getListOfPersonsByAddress());
    }

    @Test
    public void equalsTrueTest(){
        logger.info("Test equalsTrue for PersonsByAddressWithFireStationNumberDTO");
        PersonByAddressDTO personByAddressOne = new PersonByAddressDTO(
                "Enfant",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                33,
                new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}},
                new ArrayList<>() {{add("nillacilan");}});
        PersonByAddressDTO personByAddressTwo = new PersonByAddressDTO(
                "Enfant",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                33,
                new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}},
                new ArrayList<>() {{add("nillacilan");}});

        PersonsByAddressWithFireStationNumberDTO DTOForTestOne = new PersonsByAddressWithFireStationNumberDTO("3", new ArrayList<>() {{add(personByAddressOne);add(personByAddressOne);}});
        PersonsByAddressWithFireStationNumberDTO DTOForTestTwo = new PersonsByAddressWithFireStationNumberDTO("3", new ArrayList<>() {{add(personByAddressOne);add(personByAddressOne);}});

        assertEquals(DTOForTestOne, DTOForTestTwo);
        assertEquals(DTOForTestOne.hashCode(), DTOForTestTwo.hashCode());
    }

    @Test
    public void equalsNullTest(){
        logger.info("Test equalsNull for PersonsByAddressWithFireStationNumberDTO");
        PersonByAddressDTO personByAddressOne = new PersonByAddressDTO(
                "Enfant",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                33,
                new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}},
                new ArrayList<>() {{add("nillacilan");}});
        PersonByAddressDTO personByAddressTwo = new PersonByAddressDTO(
                "Enfant",
                "Petit",
                "15 rue de la rue",
                "666-777-8888",
                33,
                new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}},
                new ArrayList<>() {{add("nillacilan");}});

        PersonsByAddressWithFireStationNumberDTO DTOForTestOne = new PersonsByAddressWithFireStationNumberDTO("3", new ArrayList<>() {{add(personByAddressOne);add(personByAddressOne);}});
        PersonsByAddressWithFireStationNumberDTO DTOForTestTwo = null;

        assertNull(DTOForTestTwo);
        assertNotEquals(DTOForTestOne, DTOForTestTwo);
    }
}
