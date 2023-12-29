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
public class ChildrenWithOthersMembersOfHouseholdDTOTest {

    private static final Logger logger = LogManager.getLogger("ChildrenWithOthersMembersOfHouseholdDTOTest");

    private static ChildrenWithOthersMembersOfHouseholdDTO DTOForTest;

    String valueToTest;
    int ageToTest;
    List<OthersMembersOfTheHouseholdDTO> listToTest;

    @BeforeAll
    static void createDTOForTest(){
        logger.info("Create ChildrenWithOthersMembersOfHouseholdDTO for test");
        String firstName = "Enfant";
        String lastName = "Petit";
        int age = 5;
        String address = "15 rue de la rue";
        List<OthersMembersOfTheHouseholdDTO> listOfOthersMembersOfTheHouseholdDTO = new ArrayList<>();
        DTOForTest = new ChildrenWithOthersMembersOfHouseholdDTO(firstName, lastName, age, address, listOfOthersMembersOfTheHouseholdDTO);
    }

    @Test
    void initializeBuilderTest(){
        logger.info("Test initializeBuilder for ChildrenWithOthersMembersOfHouseholdDTO");
        assertEquals(DTOForTest, ChildrenWithOthersMembersOfHouseholdDTO.builder()
                .firstName("Enfant")
                .lastName("Petit")
                .age(5)
                .address("15 rue de la rue")
                .listOfOthersMembersOfTheHouseholdDTO(new ArrayList<>()).build());
    }

    @Test
    void setAndGetFirstNameTest(){
        logger.info("Test setAndGetFirstName for ChildrenWithOthersMembersOfHouseholdDTO");
        valueToTest = "Enfant";
        DTOForTest.setFirstName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getFirstName());
    }

    @Test
    void setAndGetLastNameTest(){
        logger.info("Test setAndGetLastName for ChildrenWithOthersMembersOfHouseholdDTO");
        valueToTest = "Petit";
        DTOForTest.setLastName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getLastName());
    }

    @Test
    void setAndGetAgeTest(){
        logger.info("Test setAndGetAge for ChildrenWithOthersMembersOfHouseholdDTO");
        ageToTest = 5;
        DTOForTest.setAge(ageToTest);
        assertEquals(ageToTest, DTOForTest.getAge());
    }

    @Test
    void setAndGetAddressTest(){
        logger.info("Test setAndGetAddress for ChildrenWithOthersMembersOfHouseholdDTO");
        valueToTest = "15 rue de la rue";
        DTOForTest.setAddress(valueToTest);
        assertEquals(valueToTest, DTOForTest.getAddress());
    }

    @Test
    void setAndGetListOfOthersMembersOfTheHouseholdDTOTest() {
        logger.info("Test setAndGetListOfOthersMembersOfTheHouseholdDTO for ChildrenWithOthersMembersOfHouseholdDTO");
        OthersMembersOfTheHouseholdDTO otherMemberOne = new OthersMembersOfTheHouseholdDTO("Toto", "Tata", "1509 Culver St");
        OthersMembersOfTheHouseholdDTO otherMemberTwo = new OthersMembersOfTheHouseholdDTO("Jean", "Miel", "1509 Culver St");
        listToTest = new ArrayList<>(){{add(otherMemberOne);add(otherMemberTwo);}};

        DTOForTest.setListOfOthersMembersOfTheHouseholdDTO(listToTest);
        assertEquals(listToTest, DTOForTest.getListOfOthersMembersOfTheHouseholdDTO());
    }

    @Test
    public void equalsTrueTest(){
        logger.info("Test equalsTrue for ChildrenWithOthersMembersOfHouseholdDTO");
        ChildrenWithOthersMembersOfHouseholdDTO DTOForTestOne = new ChildrenWithOthersMembersOfHouseholdDTO(
                "Arthur",
                "Kaamelot",
                10,
                "15 rue Paimpont",
                new ArrayList<>(){{
                    add(new OthersMembersOfTheHouseholdDTO("Toto", "Tata", "1509 Culver St"));
                    add(new OthersMembersOfTheHouseholdDTO("Jean", "Miel", "1509 Culver St"));
                }});

        ChildrenWithOthersMembersOfHouseholdDTO DTOForTestTwo = new ChildrenWithOthersMembersOfHouseholdDTO(
                "Arthur",
                "Kaamelot",
                10,
                "15 rue Paimpont",
                new ArrayList<>(){{
                    add(new OthersMembersOfTheHouseholdDTO("Toto", "Tata", "1509 Culver St"));
                    add(new OthersMembersOfTheHouseholdDTO("Jean", "Miel", "1509 Culver St"));
                }});

        assertEquals(DTOForTestOne, DTOForTestTwo);
        assertEquals(DTOForTestOne.hashCode(), DTOForTestTwo.hashCode());
    }

    @Test
    public void equalsNullTest(){
        logger.info("Test equalsNull for ChildrenWithOthersMembersOfHouseholdDTO");
        ChildrenWithOthersMembersOfHouseholdDTO DTOForTestOne = new ChildrenWithOthersMembersOfHouseholdDTO(
                "Arthur",
                "Kaamelot",
                10,
                "15 rue Paimpont",
                new ArrayList<>(){{
                    add(new OthersMembersOfTheHouseholdDTO("Toto", "Tata", "1509 Culver St"));
                    add(new OthersMembersOfTheHouseholdDTO("Jean", "Miel", "1509 Culver St"));
                }});

        ChildrenWithOthersMembersOfHouseholdDTO DTOForTestTwo = null;

        assertNull(DTOForTestTwo);
        assertNotEquals(DTOForTestOne, DTOForTestTwo);
    }
}